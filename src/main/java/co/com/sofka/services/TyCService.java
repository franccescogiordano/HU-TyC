package co.com.sofka.services;
import co.com.sofka.model.TerminosYCondiciones;
import co.com.sofka.repository.TerminosYCondicionesRepository;
import io.smallrye.mutiny.Uni;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.LocalDate;


@ApplicationScoped
public class TyCService {


    @Inject
    TerminosYCondicionesRepository repository;

    public Uni<TerminosYCondiciones> agregarTyC(TerminosYCondiciones terminosYCondiciones) {
        return repository.findAllTyC().map(cantidadVersiones ->
                new TerminosYCondiciones(terminosYCondiciones.getTexto(),
                        cantidadVersiones.intValue()+1,
                        LocalDate.now())).flatMap(repository::persist);
    }



    public Uni<TerminosYCondiciones> obtenerElUltimo(){
        return repository.findAll().list()
                .map(terminosYCondiciones -> terminosYCondiciones
                        .get(terminosYCondiciones.size()-1));
    }

}
