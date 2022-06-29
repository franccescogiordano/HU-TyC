package co.com.sofka.model;

import io.quarkus.mongodb.panache.common.MongoEntity;
import org.bson.types.ObjectId;

@MongoEntity(collection = "TipoDocumento")
public class TipoDocumento {
    public ObjectId id;
    private String regex;
    private String tipoDocumento;


    public TipoDocumento() {

    }

    public TipoDocumento(String regex, String tipoDocumento) {
        this.regex = regex;
        this.tipoDocumento = tipoDocumento;
    }

    public String getRegex() {
        return regex;
    }

    public void setRegex(String regex) {
        this.regex = regex;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

}
