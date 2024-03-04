package hsos.swa.boundary.rest;

import hsos.swa.service.PlayerService;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.jboss.logging.Logger;


import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/api/highscore")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class HighscoreResource {

    @Inject
    PlayerService playerService;
    @Inject
    Logger logger;

    /*------------------------------------------- GET --------------------------------------------*/

    @GET
    @Operation(summary = "Returns best players", description = "Returns the best players")
    @PermitAll
    @Timeout
    @Retry
    public Response getBestPlayers() {
        logger.info("Returns the best players");
        return Response.ok().entity(playerService.getBestPlayers()).build();
    }

}
