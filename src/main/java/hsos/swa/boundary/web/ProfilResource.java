package hsos.swa.boundary.web;

import hsos.swa.boundary.dto.PlayerExportDTO;
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
@Path("/profil")
public class ProfilResource {

    @Inject
    PlayerService playerService;

    @Inject
    SecurityIdentity securityIdentity;

    final static String ADMIN_ROLE = "admin";
    final static String USER_ROLE = "user";

    /*------------------------------------------- TEMPLATES --------------------------------------------*/

    @CheckedTemplate
    public static class Templates{
        public static native TemplateInstance profil(PlayerExportDTO player);

    }

    /*------------------------------------------- GET --------------------------------------------*/

    @GET
    @RolesAllowed({ADMIN_ROLE,USER_ROLE})
    public TemplateInstance getProfil(){
        Player player = playerService.getPlayer(securityIdentity.getPrincipal().getName());
        PlayerExportDTO dto = new PlayerExportDTO(player);
        return Templates.profil(dto);
    }

    /*------------------------------------------- POST --------------------------------------------*/

    @POST
    @Path("/update")
    @RolesAllowed({ADMIN_ROLE,USER_ROLE})
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public TemplateInstance updatePlayerUser(@FormParam("mail") String mail, @FormParam("password") String passwort){
        Player player = playerService.getPlayer(securityIdentity.getPrincipal().getName());
        playerService.updatePlayer(player.getId(),mail, passwort);
        PlayerExportDTO dto = new PlayerExportDTO(player);
        return Templates.profil(dto);
    }

    @POST
    @Path("/delete")
    @RolesAllowed({ADMIN_ROLE,USER_ROLE})
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public TemplateInstance deletePlayer(){
        Player player = playerService.getPlayer(securityIdentity.getPrincipal().getName());
        playerService.deletePlayer(player.getId());
        return WelcomeResource.Templates.welcome();
    }

}
