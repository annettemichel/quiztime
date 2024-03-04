package hsos.swa.boundary.rest;

import hsos.swa.boundary.dto.PlayerUserImportDTO;
import hsos.swa.entity.Player;
import hsos.swa.service.PlayerService;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameters;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/api/register")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RegisterResource {

    @Inject
    PlayerService playerService;
    @Inject
    Logger logger;

    /*------------------------------------------- POST -------------------------------------------*/

    @POST
    @Operation(summary = "Add player", description = "Register a new player")
    @Parameters({
            @Parameter(name = "mail", description = "Player email", required = true),
            @Parameter(name = "username", description = "Player username", required = true),
            @Parameter(name = "password", description = "Player password", required = true)
    })
    @Timeout
    @Retry
    public Response register(@QueryParam("mail") String mail, @QueryParam("username") String username, @QueryParam("password") String password) {
        logger.info("New player has registered with username: " + username);
        PlayerUserImportDTO playerUserImportDTO = new PlayerUserImportDTO(mail, username, password);
        Player newplayer = playerService.createPlayer(playerUserImportDTO);
        return Response.status(Response.Status.CREATED).entity(newplayer).build();

    }
}
