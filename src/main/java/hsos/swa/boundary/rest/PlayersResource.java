package hsos.swa.boundary.rest;

import hsos.swa.boundary.dto.PlayerExportDTO;
import hsos.swa.service.PlayerService;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/api/players")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PlayersResource {

    @Inject
    PlayerService playerService;
    @Inject
    Logger logger;

    /*------------------------------------------- GET --------------------------------------------*/

    @GET
    @Operation(summary = "Return players", description = "Returns all players")
    @Timeout
    @Retry
    public Response getAllPlayers() {
        logger.info("Returns all Players");
        List<PlayerExportDTO> players = playerService.getAllPlayers()
                .stream()
                .map(player -> new PlayerExportDTO(player))
                .toList();
        if (!players.isEmpty()) {
            return Response.ok(players).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

}
