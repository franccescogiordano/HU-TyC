package co.com.sofka.controller;

import co.com.sofka.model.AceptacionTyC;
import co.com.sofka.model.TerminosYCondiciones;
import co.com.sofka.services.AceptacionTyCService;
import co.com.sofka.services.TyCService;
import io.smallrye.mutiny.Uni;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.NOT_ACCEPTABLE;

@Path("/api/TyC")
@Consumes("application/json")
@Produces("application/json")
public class TyC_Controller {

    @Inject
    TyCService tYCService;



    @POST
    @Path("/cargarTyC")
    @Consumes(APPLICATION_JSON)
    public Uni<Response> crearTyC(TerminosYCondiciones terminosYCondiciones) {
        return tYCService.agregarTyC(terminosYCondiciones)
                .map(termsConditions ->Response.ok(termsConditions).build());
    }

    @GET
    @Path("/obtenerElUltimo")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> obtenerTerminosyCondiciones(){
        return tYCService.obtenerElUltimo()
                .map(termsConditions -> Response.ok(termsConditions).build());
    }

    @GET
    @Path("/obtenerPorVersion/{version}")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> obtenerPorVersion(Integer version){
        return tYCService.obtenerPorVersion(version)
                .map(termsConditions -> Response.ok(termsConditions).build());
    }


}