package hsos.swa.boundary.web;

import hsos.swa.entity.Player;
import hsos.swa.service.PlayerService;
import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;
import io.quarkus.security.identity.SecurityIdentity;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@ApplicationScoped
@Produces(MediaType.TEXT_HTML)
@Transactional
@Path("/player")
public class PlayerResource {

    @Inject
    PlayerService playerService;

    @Inject
    SecurityIdentity securityIdentity;

    final static String ADMIN_ROLE = "admin";
    final static String USER_ROLE = "user";

    /*------------------------------------------- TEMPLATES --------------------------------------------*/

    @CheckedTemplate
    public static class Templates{
        public static native TemplateInstance playersearch(Player player);
        public static native TemplateInstance playerdetail(Player player);
    }

    /*------------------------------------------- GET --------------------------------------------*/

    @GET
    @Path("{playerId}")
    @RolesAllowed(ADMIN_ROLE)
    public TemplateInstance getPlayer(@PathParam("playerId") Long id){
        return Templates.playerdetail(playerService.getPlayer(id));
    }

    /*------------------------------------------- POST --------------------------------------------*/

    @POST
    @Path("{playerId}/update")
    @RolesAllowed(ADMIN_ROLE)
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public TemplateInstance updatePlayer(@PathParam("playerId") long id, @FormParam("mail") String mail, @FormParam("password") String passwort, @FormParam("role") String role){
        playerService.updatePlayerAdmin(id, mail, passwort, role);
        return Templates.playerdetail(playerService.getPlayer(id));
    }

    @POST
    @Path("/search")
    @RolesAllowed(ADMIN_ROLE)
    public TemplateInstance searchPlayer(@FormParam("username") String username){
        Player searchedPlayer = playerService.getPlayer(username);
        return Templates.playersearch(searchedPlayer);
    }

    @POST
    @Path("delete/{playerId}")
    @RolesAllowed(ADMIN_ROLE)
    public TemplateInstance deletePlayer(@PathParam("playerId") Long playerId){
        playerService.deletePlayer(playerId);
        return PlayersResource.Templates.players(playerService.getAllPlayers());
    }

    @POST
    @Path("{playerId}/delete")
    @RolesAllowed(ADMIN_ROLE)
    public TemplateInstance deletePlayerDetail(@PathParam("playerId") Long playerId){
        playerService.deletePlayer(playerId);
        return PlayersResource.Templates.players(playerService.getAllPlayers());
    }

}
