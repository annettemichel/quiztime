package hsos.swa.boundary.web;

import hsos.swa.boundary.dto.PlayerExportDTO;
import hsos.swa.entity.Player;
import hsos.swa.boundary.dto.PlayerAdminImportDTO;
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
import java.util.Collection;
import java.util.List;

@ApplicationScoped
@Produces(MediaType.TEXT_HTML)
@Transactional
@Path("/players")
public class PlayersResource {

    @Inject
    PlayerService playerService;

    @Inject
    SecurityIdentity securityIdentity;

    final static String ADMIN_ROLE = "admin";
    final static String USER_ROLE = "user";

    /*------------------------------------------- TEMPLATES --------------------------------------------*/

    @CheckedTemplate
    public static class Templates{
        public static native TemplateInstance players(Collection<Player> players);

    }

    /*------------------------------------------- GET --------------------------------------------*/

    @GET
    @RolesAllowed(ADMIN_ROLE)
    public TemplateInstance getAllPlayer(){
        List<PlayerExportDTO> playerList = playerService.getAllPlayers()
                .stream()
                .map(player -> new PlayerExportDTO(player))
                .toList();
        return Templates.players(playerService.getAllPlayers());
    }

    /*------------------------------------------- POST -------------------------------------------*/

    @POST
    @RolesAllowed(ADMIN_ROLE)
    public TemplateInstance createPlayerAdmin(@FormParam("mail") String mail, @FormParam("username") String username, @FormParam("password") String password, @FormParam("role") String role) {
        PlayerAdminImportDTO playerAdminImportDTO = new PlayerAdminImportDTO(mail,username,password,role);
        playerService.createPlayer(playerAdminImportDTO);
        return Templates.players(playerService.getAllPlayers());
    }

}
