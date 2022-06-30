package co.com.sofka.services;

import co.com.sofka.model.TerminosYCondiciones;
import co.com.sofka.model.TipoDocumento;
import co.com.sofka.repository.TerminosYCondicionesRepository;
import co.com.sofka.repository.TipoDocumentoRepository;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.List;

@ApplicationScoped
public class TipoDocumentoService {
    @Inject
    TipoDocumentoRepository repository;

    public Uni<? extends TipoDocumento> agregarTipoDoc(TipoDocumento tipoDocumento) {
        return repository.persist(tipoDocumento);
    }


    public Uni<List<TipoDocumento>> buscarTodos(){
        return repository.findTodos();
    }
    public Uni<TipoDocumento> obtenerporTipoDocumento(String tipo) {
        return repository.findByTipoDocumento(tipo).map(tipoDocumento -> tipoDocumento);
    }
}
