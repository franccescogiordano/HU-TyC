package co.com.sofka.repository;

import co.com.sofka.model.TerminosYCondiciones;
import io.quarkus.mongodb.panache.PanacheMongoRepositoryBase;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoRepository;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoRepositoryBase;
import io.smallrye.mutiny.Uni;
import org.bson.types.ObjectId;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;
@ApplicationScoped
public class TerminosYCondicionesRepository implements ReactivePanacheMongoRepository<TerminosYCondiciones> {
    public Uni<Integer> findAllTyC(){
        return  findAll().count().onItem().transform(size->size.intValue());
    }


}
