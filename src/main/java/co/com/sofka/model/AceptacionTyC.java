package co.com.sofka.model;
import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.types.ObjectId;

import java.time.Instant;

@MongoEntity(collection = "AceptacionTyC")
public class AceptacionTyC {
    public ObjectId id;
    private String tipoDocumento;
    private String numDoc;
    private Instant fechaAceptacion;
    private String versionTyC;

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNumDoc() {
        return numDoc;
    }

    public void setNumDoc(String numDoc) {
        this.numDoc = numDoc;
    }

    public Instant getFechaAceptacion() {
        return fechaAceptacion;
    }

    public void setFechaAceptacion(Instant fechaAceptacion) {
        this.fechaAceptacion = fechaAceptacion;
    }

    public AceptacionTyC(String tipoDocumento, String numDoc, Instant fechaAceptacion, String versionTyC)
 {
        this.tipoDocumento = tipoDocumento;
        this.numDoc = numDoc;
        this.fechaAceptacion = fechaAceptacion;
        this.versionTyC=versionTyC;
    }

    public AceptacionTyC(ObjectId id, String tipoDocumento, String numDoc, Instant fechaAceptacion, String versionTyC) {
        this.id = id;
        this.tipoDocumento = tipoDocumento;
        this.numDoc = numDoc;
        this.fechaAceptacion = fechaAceptacion;
        this.versionTyC = versionTyC;
    }

    public AceptacionTyC() {

    }

    public String getVersionTyC() {
        return versionTyC;
    }

    public void setVersionTyC(String versionTyC) {
        this.versionTyC = versionTyC;
    }
}
