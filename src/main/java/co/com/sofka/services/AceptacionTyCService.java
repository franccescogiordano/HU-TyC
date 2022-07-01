package co.com.sofka.services;

import co.com.sofka.model.AceptacionTyC;
import co.com.sofka.model.DTOTrucheli;
import co.com.sofka.model.TipoDocumento;
import co.com.sofka.repository.AceptacionRepository;
import co.com.sofka.repository.TipoDocumentoRepository;
import io.smallrye.common.annotation.Blocking;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.subscription.Cancellable;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@ApplicationScoped
public class AceptacionTyCService {

    @Inject
    AceptacionRepository repository;
    @Inject
    TipoDocumentoRepository repositorytipo;

    public Uni<Object> agregarAceptacion(AceptacionTyC aceptTyC) {
        System.out.println("ENTRE AL IF DE AGREGAR ACEPTACION");
        return Uni.createFrom().item(aceptTyC)
                .flatMap(aceptacion -> {
                    aceptacion.setFechaAceptacion(Instant.now());
                    if (aceptacion.getTipoDocumento().equalsIgnoreCase("Cedula")) {
                        System.out.println("ENTRE AL IF  DE CEDULA ");
                        return agregarAceptacionConPasaporte(aceptacion);

                    }
                    aceptacion.setFechaAceptacion(ZonedDateTime.now().toInstant());
                    return agregarAceptacionConPasaporte(aceptacion);
                });
    }



    public Uni<DTOTrucheli> agregarAceptacionConPasaporte(AceptacionTyC aceptTyC) {

      return  repositorytipo.findByTipoDocumento(aceptTyC.getTipoDocumento()).flatMap(x -> {
            Pattern pattern = Pattern.compile(x.getRegex());
            Matcher matcher = pattern.matcher(aceptTyC.getNumDoc());
            Boolean verificar = matcher.matches();

            if (verificar) {
                return Uni.createFrom()
                        .item(aceptTyC)
                        .flatMap(repository::persist)
                        .map(aceptacion -> new DTOTrucheli("Acepto terminos y condiciones con el documento " + aceptTyC.getNumDoc() + "Documento verificado del tipo " + aceptTyC.getTipoDocumento()));
            }
            throw new IllegalArgumentException();
        });

    }


}
