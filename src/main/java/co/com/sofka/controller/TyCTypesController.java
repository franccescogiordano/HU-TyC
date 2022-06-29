package co.com.sofka.controller;

import co.com.sofka.model.TerminosYCondiciones;
import co.com.sofka.model.TipoDocumento;
import co.com.sofka.services.TipoDocumentoService;

import io.smallrye.mutiny.Uni;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/api/TyC")
@Consumes("application/json")
@Produces("application/json")
public class TyCTypesController {
    @Inject
    TipoDocumentoService service;
    
    @POST
    @Path("/cargarTyC")
    @Consumes(APPLICATION_JSON)
    public Uni<Response> crearTyC(TipoDocumento tipoDoc) {
        return service.agregarTipoDoc(tipoDoc)
                .map(termsConditions ->Response.ok(termsConditions).build());
    }

    @GET
    @Path("/todos")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> obtenerTerminosyCondiciones(){
        return service.buscarTodos()
                .map(termsConditions -> Response.ok(termsConditions).build());
    }

    @GET
    @Path("/obtenerPorTipo/{tipoDocumento}")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> obtenerporTipoDocumento(String tipoDocumento){
        return service.obtenerporTipoDocumento(tipoDocumento)
                .map(termsConditions -> Response.ok(termsConditions).build());
    }

}
