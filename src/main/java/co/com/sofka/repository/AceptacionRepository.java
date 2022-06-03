package co.com.sofka.repository;

import co.com.sofka.model.AceptacionTyC;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoRepositoryBase;
import org.bson.types.ObjectId;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AceptacionRepository implements ReactivePanacheMongoRepositoryBase<AceptacionTyC, ObjectId> {
}
