package co.com.sofka.model;

import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.types.ObjectId;

import java.time.Instant;

@MongoEntity(collection = "TerminosYCondiciones")
public class TerminosYCondiciones {
    public ObjectId id;
    private String texto;
    private Integer version;
    private Instant fechaCreacion;

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Instant getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Instant fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
    public TerminosYCondiciones(){

    }

    public TerminosYCondiciones(String texto, Integer version, Instant fechaCreacion) {
        this.texto = texto;
        this.version = version;
        this.fechaCreacion = fechaCreacion;
    }

    public TerminosYCondiciones(ObjectId id, String texto, Integer version, Instant fechaCreacion) {
        this.texto = texto;
        this.version = version;
        this.fechaCreacion = fechaCreacion;
        this.id=id;
    }

}
