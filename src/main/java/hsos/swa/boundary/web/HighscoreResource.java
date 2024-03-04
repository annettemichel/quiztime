package hsos.swa.boundary.web;

import hsos.swa.boundary.dto.PlayerExportDTO;
import hsos.swa.entity.Player;
import hsos.swa.service.PlayerService;

import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;
import io.quarkus.security.identity.SecurityIdentity;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import java.util.List;


@Path("/highscore")
@ApplicationScoped
@Produces(MediaType.TEXT_HTML)
@Transactional
public class HighscoreResource {
    @Inject
    PlayerService playerService;
    @Inject
    SecurityIdentity securityIdentity;

    final static String ADMIN_ROLE = "admin";
    final static String USER_ROLE = "user";

    /*------------------------------------------- TEMPLATES --------------------------------------------*/

    @CheckedTemplate
    public static class Templates {
        public static native TemplateInstance highscoreLoggedIn(List<PlayerExportDTO> players, PlayerExportDTO player);

        public static native TemplateInstance highscore(List<PlayerExportDTO> players);
    }

    /*------------------------------------------- GET --------------------------------------------*/

    @GET
    public TemplateInstance getBestPlayers() {

        List<PlayerExportDTO> playerList = playerService.getBestPlayers()
                .stream()
                .map(PlayerExportDTO::new)
                .toList();

        if (securityIdentity.getPrincipal().getName().isEmpty()) {
            return Templates.highscore(playerList);
        }

        Player player = playerService.getPlayer(securityIdentity.getPrincipal().getName());
        PlayerExportDTO dto = new PlayerExportDTO(player);

        return Templates.highscoreLoggedIn(playerList,dto);
    }



}
