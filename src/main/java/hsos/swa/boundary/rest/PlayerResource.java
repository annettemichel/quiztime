package hsos.swa.boundary.rest;


import hsos.swa.entity.Player;
import hsos.swa.boundary.dto.PlayerUserImportDTO;
import hsos.swa.service.PlayerService;

import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameters;
import org.jboss.logging.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.inject.Inject;
import javax.ws.rs.core.Response;

@Path("/api/player")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PlayerResource {

    @Inject
    PlayerService playerService;
    @Inject
    Logger logger;

    /*------------------------------------------- GET --------------------------------------------*/

    @GET
    @Path("{id}")
    @Operation(summary = "Returns single player with id", description = "Returns a single player with id")
    @Parameter(name = "id", description = "Player Id", required = true)
    @Timeout
    @Retry
    public Response getPlayer(@PathParam("id") Long id) {
        logger.info("Returns a single Player with id " + id);
        return Response.ok().entity(playerService.getPlayer(id)).build();
    }

    /*------------------------------------------- PUT --------------------------------------------*/

    @PUT
    @Path("{id}")
    @Operation(summary = "Edit player by Id", description = "Edit player with Id")
    @Parameters({
            @Parameter(name = "id", description = "Player Id", required = true),
            @Parameter(name = "mail", description = "Player email", required = true),
            @Parameter(name = "password", description = "Player password", required = true)
    })
    @Timeout
    @Retry
    public Response updatePlayerInformation(@PathParam("id") Long id, @QueryParam("mail") String mail, @QueryParam("password") String password) {
        logger.info("Edit player with Id: " + id);
        Player updatedPlayer = playerService.updatePlayer(id, mail, password);
        return Response.ok().entity(updatedPlayer).build();
    }

    @PUT
    @Path("{id}")
    @Operation(summary = "Increase score", description = "Increase score for player with Id")
    @Parameters({
            @Parameter(name = "id", description = "Player Id", required = true),
            @Parameter(name = "points", description = "add points", required = true)
    })
    @Timeout
    @Retry
    public void increaseScore(@PathParam("id") Long id, @QueryParam("points") int points) {
        logger.info("Increase score by" + points + " points for player with Id: " + id);
        playerService.increaseScore(id, points);
    }

    @PUT
    @Path("{id}")
    @Operation(summary = "Substract score", description = "Substract score for player with Id")
    @Parameters({
            @Parameter(name = "id", description = "Player Id", required = true),
            @Parameter(name = "points", description = "Remove points", required = true)
    })
    @Timeout
    @Retry
    public void subtractScore(@PathParam("id") Long id, @QueryParam("points") int points) {
        logger.info("Substract score by" + points + " points for player with Id: " + id);
        playerService.subtractScore(id, points);
    }

    /*------------------------------------------- POST -------------------------------------------*/

    @POST
    @Operation(summary = "create player", description = "Create new player")
    @Parameters({
            @Parameter(name = "id", description = "Player Id", required = true),
            @Parameter(name = "mail", description = "Player email", required = true),
            @Parameter(name = "username", description = "Player username", required = true),
            @Parameter(name = "password", description = "Player password", required = true)
    })
    @Timeout
    @Retry
    public Response createPlayer(@QueryParam("mail") String mail, @QueryParam("username") String username, @QueryParam("password") String password) {
        logger.info("Create new player with username: " + username);
        PlayerUserImportDTO playerUserImportDTO = new PlayerUserImportDTO(mail, username, password);
        Player newplayer = playerService.createPlayer(playerUserImportDTO);
        return Response.status(Response.Status.CREATED).entity(newplayer).build();
    }

    /*------------------------------------------ DELETE ------------------------------------------*/

    @DELETE
    @Operation(summary = "Delete Player", description = "Delete player with id")
    @Parameter(name = "id", description = "Player Id", required = true)
    @Timeout
    @Retry
    public void deletePlayer(@QueryParam("id") long id) {
        logger.info("Delete player with id: " + id);
        playerService.deletePlayer(id);
    }

}
