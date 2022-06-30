package co.com.sofka.repository;

import co.com.sofka.model.TerminosYCondiciones;
import co.com.sofka.model.TipoDocumento;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoRepositoryBase;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import org.bson.types.ObjectId;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
@ApplicationScoped
public class TipoDocumentoRepository implements ReactivePanacheMongoRepositoryBase<TipoDocumento, ObjectId> {
   public Uni<TipoDocumento>  findByTipoDocumento(String tipo){return find("tipoDocumento",tipo).firstResult();}
   public Uni<List<TipoDocumento>> findTodos(){   return findAll().list();     }

   // public Uni<TerminosYCondiciones> findByVersion(Integer version){return find("version",version).firstResult();};
}
