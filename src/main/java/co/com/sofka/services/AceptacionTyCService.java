package co.com.sofka.services;

import co.com.sofka.model.AceptacionTyC;
import co.com.sofka.model.DTOTrucheli;
import co.com.sofka.repository.AceptacionRepository;
import io.smallrye.mutiny.Uni;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Produces;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
@ApplicationScoped
public class AceptacionTyCService {

    @Inject
    AceptacionRepository repository;

    public Uni<Object> agregarAceptacion(AceptacionTyC aceptTyC){

        return Uni.createFrom().item(aceptTyC)
                .flatMap(aceptacion->{
                    aceptacion.setFechaAceptacion(LocalDate.now());
                    if (aceptacion.getTipoDocumento().equalsIgnoreCase("Cedula")){
                        return agregarAceptacionConCedula(aceptacion);
                    }
                    aceptacion.setFechaAceptacion(LocalDate.now());
                    return agregarAceptacionConPasaporte(aceptacion);
                });
    }

    public Uni<DTOTrucheli> agregarAceptacionConCedula(AceptacionTyC aceptTyC){
        String regex ="[0-9]{2}-PN-[0-9]{3}-[0-9]{4}";
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

    public Uni<DTOTrucheli> agregarAceptacionConPasaporte(AceptacionTyC aceptTyC){
        String regex ="[a-zA-Z0-9-]{5,16}";
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
