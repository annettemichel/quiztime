package hsos.swa.boundary.web;

import hsos.swa.boundary.dto.PlayerUserImportDTO;
import hsos.swa.service.PlayerService;
import io.quarkus.elytron.security.common.BcryptUtil;
import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;
import io.quarkus.security.identity.SecurityIdentity;

import javax.annotation.security.PermitAll;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


@ApplicationScoped
@Produces(MediaType.TEXT_HTML)
@Transactional
@Path("/register")
public class RegisterResource {

    @Inject
    PlayerService playerService;
    @Inject
    SecurityIdentity securityIdentity;


    /*------------------------------------------- TEMPLATES --------------------------------------------*/

    @CheckedTemplate
    public static class Templates{
        public static native TemplateInstance register();
    }

    /*------------------------------------------- GET --------------------------------------------*/

    @GET
    @PermitAll
    public TemplateInstance getRegister(){
        return Templates.register();
    }

    /*------------------------------------------- POST -------------------------------------------*/

    @POST
    @PermitAll
    public TemplateInstance createPlayerUser(@FormParam("mail") String mail, @FormParam("username") String username, @FormParam("password") String password){
        PlayerUserImportDTO playerUserImportDTO = new PlayerUserImportDTO(mail,username,password);
        System.out.println(BcryptUtil.bcryptHash(password));
        if (playerService.createPlayer(playerUserImportDTO) == null) {
            return Templates.register();
        }
        return WelcomeResource.Templates.welcome();
    }

}
