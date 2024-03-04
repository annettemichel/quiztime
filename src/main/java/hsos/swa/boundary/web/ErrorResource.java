package hsos.swa.boundary.web;


import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;
import javax.annotation.security.PermitAll;
import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;


@ApplicationScoped
@Produces(MediaType.TEXT_HTML)
@Transactional
@Path("/error")
public class ErrorResource {


    /*------------------------------------------- TEMPLATES --------------------------------------------*/

    @CheckedTemplate
    public static class Templates{
        public static native TemplateInstance error();
    }

    /*------------------------------------------- GET --------------------------------------------*/

    @GET
    @PermitAll
    public TemplateInstance getError(){
        return Templates.error();
    }


}
