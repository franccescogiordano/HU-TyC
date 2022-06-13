package co.com.sofka.controller;

import co.com.sofka.model.AceptacionTyC;
import co.com.sofka.services.AceptacionTyCService;
import io.smallrye.mutiny.Uni;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.Status.NOT_ACCEPTABLE;

@Path("/api/ATyC")
@Consumes("application/json")
@Produces("application/json")
public class ATyC_Controller {


    @Inject
    AceptacionTyCService aceptacionTyCService;


    @POST
    @Path("/agregarAceptacion")
    @Consumes("application/json")
    public Uni<Response> cargarAceptacion(AceptacionTyC aceptacionTyC) {
        if(aceptacionTyC.getTipoDocumento().equalsIgnoreCase("Cedula")||
                aceptacionTyC.getTipoDocumento().equalsIgnoreCase("Pasaporte")){

            return aceptacionTyCService.agregarAceptacion(aceptacionTyC)
                    .map(acepTermsC -> Response.ok(acepTermsC).build())
                    .onFailure().
                    recoverWithItem(() -> Response.status(406).build());
        }
        return Uni.createFrom().item(Response.status(NOT_ACCEPTABLE).build());


    }

}
