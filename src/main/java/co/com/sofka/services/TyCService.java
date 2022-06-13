package co.com.sofka.services;
import co.com.sofka.model.TerminosYCondiciones;
import co.com.sofka.repository.TerminosYCondicionesRepository;
import io.smallrye.mutiny.Uni;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.*;


@ApplicationScoped
public class TyCService {

    Instant instant = Instant.now();
    @Inject
    TerminosYCondicionesRepository repository;

    public Uni<? extends TerminosYCondiciones> agregarTyC(TerminosYCondiciones terminosYCondiciones) {
        return repository.findAllTyC().map(cantidadVersiones ->
                new TerminosYCondiciones(terminosYCondiciones.getTexto(),
                        cantidadVersiones.intValue()+1,
                        obtenerFecha())).flatMap(repository::persist);
    }

    public Instant obtenerFecha(){
        return  ZonedDateTime.now().toInstant();
    }

    public Uni<TerminosYCondiciones> obtenerPorVersion(Integer version) {
        return repository.findByVersion(version).map(terminosYCondiciones -> terminosYCondiciones);
    }

    public Uni<TerminosYCondiciones> obtenerElUltimo(){
        return repository.findAll().list()
                .map(terminosYCondiciones -> terminosYCondiciones
                        .get(terminosYCondiciones.size()-1));
    }

}
