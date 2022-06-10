package co.com.sofka;

import co.com.sofka.model.AceptacionTyC;
import co.com.sofka.model.TerminosYCondiciones;
import co.com.sofka.repository.AceptacionRepository;
import co.com.sofka.services.AceptacionTyCService;
import co.com.sofka.services.TyCService;
import io.smallrye.mutiny.Uni;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.Response.Status.NOT_ACCEPTABLE;

@Path("/api/TyC")
@Consumes("application/json")
@Produces("application/json")
public class controller {

    @Inject
    TyCService tYCService;

    @Inject
    AceptacionTyCService aceptacionTyCService;

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
    @POST
    @Path("/agregarAceptacion")
    @Consumes("application/json")
    public Uni<Response> cargarAceptacion(AceptacionTyC aceptacionTyC) {
      if(aceptacionTyC.getTipoDocumento().equalsIgnoreCase("Cedula")||
                aceptacionTyC.getTipoDocumento().equalsIgnoreCase("Pasaporte")){

            return aceptacionTyCService.agregarAceptacion(aceptacionTyC)
                    .map(acepTermsC -> Response.ok(acepTermsC).build())
                    .onFailure().
                    recoverWithItem(() -> Response.status(500).build());
        }
        return Uni.createFrom().item(Response.status(NOT_ACCEPTABLE).build());


    }

}