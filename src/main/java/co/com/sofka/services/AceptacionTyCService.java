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
    public Uni<Object> agregarAceptacion(AceptacionTyC aceptTyC){
        System.out.println("ENTRE AL IF DE AGREGAR ACEPTACION");
        return Uni.createFrom().item(aceptTyC)
                .flatMap(aceptacion->{
                    aceptacion.setFechaAceptacion(Instant.now());
                    if (aceptacion.getTipoDocumento().equalsIgnoreCase("Cedula")){
                        System.out.println("ENTRE AL IF  DE CEDULA ");
                        return  repositorytipo.findByTipoDocumento("Cedula").map(tipoDocumento -> agregarAceptacionConCedula(tipoDocumento.getRegex()));

                    }
                    aceptacion.setFechaAceptacion(ZonedDateTime.now().toInstant());
                    return agregarAceptacionConPasaporte(aceptacion);
                });
    }


    @Blocking
    public Uni<DTOTrucheli> agregarAceptacionConCedula(AceptacionTyC aceptTyC){
       // String regex ="[0-9]{2}-PN-[0-9]{3}-[0-9]{4}";
        String aaa="aaaaa";


        //String regex= repositorytipo.findByTipoDocumento("Cedula").map(tipoDocumento -> tipoDocumento).await().indefinitely().getRegex();


       repositorytipo.findByTipoDocumento("Cedula").map(x->x.getRegex()).subscribe().with(s -> {

       });
      
      
        String regex="";
        Pattern pattern = Pattern.compile(aaa);
        Matcher matcher = pattern.matcher(aceptTyC.getNumDoc());
        Boolean verificar = matcher.matches();

        if (verificar){
            return Uni.createFrom()
                    .item(aceptTyC)
                    .flatMap(repository::persist)
                    .map(aceptacion -> new DTOTrucheli("Acepto terminos y condiciones con el documento "+aceptTyC.getNumDoc()+ "Documento verificado del tipo "+aceptTyC.getTipoDocumento()));
        }
        throw new IllegalArgumentException();
    }

    public Uni<DTOTrucheli> agregarAceptacionConPasaporte(AceptacionTyC aceptTyC){


        System.out.println("ZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZZ");
    //int result2 = uni
        //        .flatMap(i -> Uni.createFrom().item(i + 1))
        //        .await().indefinitely();
    String regex= repositorytipo.findByTipoDocumento("Pasaporte").map(tipoDocumento -> tipoDocumento).toString();
      //String regex = repositorytipo.findByTipoDocumento("Pasaporte").toString();

        System.out.println("aaaaaaaaaaaaaaaaAAAAAAAAAAAAAAAAAAA"+regex);
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(aceptTyC.getNumDoc());
        Boolean verificar = matcher.matches();

        if (verificar){
            return Uni.createFrom()
                    .item(aceptTyC)
                    .flatMap(repository::persist)
                    .map(aceptacion -> new DTOTrucheli("Acepto terminos y condiciones con el documento "+aceptTyC.getNumDoc()+ "Documento verificado del tipo "+aceptTyC.getTipoDocumento()));
        }
        throw new IllegalArgumentException();
    }


}
