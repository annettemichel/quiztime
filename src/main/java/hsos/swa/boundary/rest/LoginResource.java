package hsos.swa.boundary.rest;

import hsos.swa.service.PlayerService;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.jboss.logging.Logger;

import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

@Path("/api/login")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LoginResource {

    @Inject
    PlayerService playerService;
    @Inject
    Logger logger;

    /*------------------------------------------- POST -------------------------------------------*/

    @POST
    @Path("/profil")
    @PermitAll
    @Operation(summary = "Returns the profile", description = "Returns the profile of the logged in user")
    @Timeout
    @Retry
    public String profil(@Context SecurityContext securityContext) {
        return securityContext.getUserPrincipal().getName();
    }

}
