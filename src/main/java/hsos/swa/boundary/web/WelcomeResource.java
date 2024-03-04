package hsos.swa.boundary.web;

import hsos.swa.boundary.dto.PlayerExportDTO;
import hsos.swa.entity.Player;
import hsos.swa.service.PlayerService;
import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;
import io.quarkus.security.identity.SecurityIdentity;

import javax.annotation.security.PermitAll;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/welcome")
@ApplicationScoped
@Produces(MediaType.TEXT_HTML)
@Transactional
public class WelcomeResource {

    @Inject
    PlayerService playerService;
    @Inject
    SecurityIdentity securityIdentity;

    /*------------------------------------------- TEMPLATES --------------------------------------------*/

    @CheckedTemplate
    public static class Templates {
        public static native TemplateInstance welcome();

        public static native TemplateInstance welcomeLoggedIn(PlayerExportDTO player);
    }

    /*------------------------------------------- GET --------------------------------------------*/

    @GET
    @PermitAll
    public TemplateInstance getWelcome() {
        if (securityIdentity.getPrincipal().getName().isEmpty()) {
            return Templates.welcome();
        }
        Player player = playerService.getPlayer(securityIdentity.getPrincipal().getName());
        PlayerExportDTO dto = new PlayerExportDTO(player);
        return Templates.welcomeLoggedIn(dto);
    }
}
