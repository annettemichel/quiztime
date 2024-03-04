package hsos.swa.boundary.web;

import hsos.swa.service.PlayerService;
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
@Path("/login")
public class LoginResource {


    @Inject
    PlayerService playerService;
    @Inject
    SecurityIdentity securityIdentity;

    /*------------------------------------------- TEMPLATES --------------------------------------------*/

    @CheckedTemplate
    public static class Templates{
        public static native TemplateInstance login();
    }

    /*------------------------------------------- GET --------------------------------------------*/

    @GET
    @PermitAll
    public TemplateInstance getLogin(){
        return Templates.login();
    }

    /*------------------------------------------- POST -------------------------------------------*/

    @POST
    @PermitAll
    public TemplateInstance login(@FormParam("username") String  username,@FormParam("password") String password){
        return WelcomeResource.Templates.welcome();
    }
}
