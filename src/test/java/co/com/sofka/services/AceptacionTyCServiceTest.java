package co.com.sofka.services;

import co.com.sofka.model.AceptacionTyC;
import co.com.sofka.repository.AceptacionRepository;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import io.smallrye.mutiny.Uni;
import org.bson.types.ObjectId;
import org.gradle.internal.impldep.javax.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class AceptacionTyCServiceTest {

    @InjectMock
    AceptacionTyCService service;

    @InjectMock
    AceptacionRepository repository;

    private AceptacionTyC aceptacionTyC;
    private AceptacionTyC aceptacionTyC2;

    private final LocalDate date=LocalDate.now();

    private final ObjectId id = new ObjectId("62a0c1034a8d71536568fdf4");

    private final String v = "8";

    private final String tipodoc = "Cedula";
    private final String tipodoc2 = "Pasaporte";

    private final String documento = "02-PN-010-1234";
    private final String documento2 = "dadasda111-";

    @BeforeEach
    void setUp() {
    aceptacionTyC= new AceptacionTyC(id,tipodoc,documento,date,v);
    aceptacionTyC2= new AceptacionTyC(id,tipodoc2,documento2,date,v);

    }

    @Test
    void agregarAceptacion() {

        Mockito.when(repository.persist(aceptacionTyC)).thenReturn(Uni.createFrom().item(aceptacionTyC));

        service.agregarAceptacion(aceptacionTyC).subscribe().with(dx -> {
            Assertions.assertNotNull(dx);
        });

    }

    @Test
    void agregarAceptacionConCedula() {
        Mockito.when(repository.persist(aceptacionTyC)).thenReturn(Uni.createFrom().item(aceptacionTyC));

        service.agregarAceptacionConCedula(aceptacionTyC).subscribe().with(dx -> {
            Assertions.assertNotNull(dx);
        });
    }

    @Test
    void agregarAceptacionConPasaporte() {
        Mockito.when(repository.persist(aceptacionTyC2)).thenReturn(Uni.createFrom().item(aceptacionTyC2));

        service.agregarAceptacionConPasaporte(aceptacionTyC2).subscribe().with(dx -> {
            Assertions.assertNotNull(dx);
        });
    }
}