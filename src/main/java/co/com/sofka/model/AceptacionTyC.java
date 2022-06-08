package co.com.sofka.model;
import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.types.ObjectId;
import java.time.LocalDate;
@MongoEntity(collection = "AceptacionTyC")
public class AceptacionTyC {
    public ObjectId id;
    private String tipoDocumento;
    private String numDoc;
    private LocalDate fechaAceptacion;
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

    public LocalDate getFechaAceptacion() {
        return fechaAceptacion;
    }

    public void setFechaAceptacion(LocalDate fechaAceptacion) {
        this.fechaAceptacion = fechaAceptacion;
    }

    public AceptacionTyC(String tipoDocumento, String numDoc, LocalDate fechaAceptacion,String versionTyC)
 {
        this.tipoDocumento = tipoDocumento;
        this.numDoc = numDoc;
        this.fechaAceptacion = fechaAceptacion;
        this.versionTyC=versionTyC;
    }

    public AceptacionTyC(ObjectId id, String tipoDocumento, String numDoc, LocalDate fechaAceptacion, String versionTyC) {
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
