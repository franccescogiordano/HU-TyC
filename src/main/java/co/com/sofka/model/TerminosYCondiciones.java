package co.com.sofka.model;

import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.types.ObjectId;

import java.time.LocalDate;

@MongoEntity(collection = "TerminosYCondiciones")
public class TerminosYCondiciones {
    public ObjectId id;
    private String texto;
    private Integer version;
    private LocalDate fechaCreacion;

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
    public TerminosYCondiciones(){

    }

    public TerminosYCondiciones(String texto, Integer version, LocalDate fechaCreacion) {
        this.texto = texto;
        this.version = version;
        this.fechaCreacion = fechaCreacion;
    }
}
