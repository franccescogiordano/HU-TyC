package co.com.sofka.services;

import co.com.sofka.model.TerminosYCondiciones;
import co.com.sofka.repository.TerminosYCondicionesRepository;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import io.smallrye.mutiny.Uni;
import org.bson.types.ObjectId;
import org.gradle.internal.impldep.javax.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@QuarkusTest
class TyCServiceTest {

    @InjectMock
    TyCService service;

    @InjectMock
    TerminosYCondicionesRepository repository;

    private TerminosYCondiciones tyc;

    private   Uni<List> uniList;

    private List<TerminosYCondiciones> list;
    private final LocalDate date = LocalDate.now();
    private final ObjectId id = new ObjectId("62a0c1034a8d71536568fdf4");
    private final String txt = "Terminos y condiciones version 8";
    private final Integer v = 8;


    @BeforeEach
    void setUp() {
        //ObjectId id,String texto, Integer version, LocalDate fechaCreacion
        MockitoAnnotations.openMocks(this);

        tyc= new TerminosYCondiciones(id,txt,v,date);

         list= new ArrayList<>();
         list.add(tyc);
        uniList=Uni.createFrom().item(list);
    }

    @Test
    void agregarTyC() {
        Mockito.when(repository.findAllTyC()).thenReturn(Uni.createFrom().item(1));
        Mockito.when(repository.persist(tyc)).thenReturn(Uni.createFrom().item(tyc));

        service.agregarTyC(tyc).subscribe().with(tyc -> {
            Assertions.assertEquals(txt, tyc.getTexto());
            Assertions.assertEquals(v, tyc.getVersion());
            Assertions.assertEquals(id, tyc.getId());
            Assertions.assertEquals(date, tyc.getFechaCreacion());

        });
    }

    @Test
    void obtenerPorVersion() {
        Mockito.when(repository.findByVersion(8)).thenReturn(Uni.createFrom().item(tyc));

        service.obtenerPorVersion(8).subscribe().with(tyc -> {
            Assertions.assertEquals(txt, tyc.getTexto());
            Assertions.assertEquals(v, tyc.getVersion());
            Assertions.assertEquals(id, tyc.getId());
            Assertions.assertEquals(date, tyc.getFechaCreacion());
        });
    }

    @Test
    void obtenerElUltimo() {

        LocalDate date = LocalDate.now();

        List<TerminosYCondiciones> list = new ArrayList<>();
        list.add(new TerminosYCondiciones(txt,1,date));
        list.add(new TerminosYCondiciones("x",v, date));
        list.add(new TerminosYCondiciones("d",9, date));

        Mockito.when(repository.listAll()).thenReturn(Uni.createFrom().item(list));

        service.obtenerElUltimo().subscribe().with(tyc -> {
            Assertions.assertEquals(1,tyc.getVersion());
        });
    }
}