package hsos.swa.boundary.web;

import hsos.swa.boundary.dto.PlayerExportDTO;
import hsos.swa.entity.Player;
import hsos.swa.entity.Quiz;
import hsos.swa.service.PlayerService;
import hsos.swa.service.QuizService;
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


@ApplicationScoped
@Produces(MediaType.TEXT_HTML)
@Transactional
@Path("/quiz")
public class QuizResource {

    @Inject
    QuizService quizService;
    @Inject
    PlayerService playerService;

    @Inject
    SecurityIdentity securityIdentity;

    final static String ADMIN_ROLE = "admin";
    final static String USER_ROLE = "user";

    /*------------------------------------------- TEMPLATES --------------------------------------------*/

    @CheckedTemplate
    public static class Templates {
        public static native TemplateInstance startquiz(Collection<Quiz> quizzes, PlayerExportDTO player);
        public static native TemplateInstance quiz(PlayerExportDTO player);
    }

    /*------------------------------------------- GET --------------------------------------------*/

    @GET
    @RolesAllowed({ADMIN_ROLE,USER_ROLE})
    public TemplateInstance quiz() {
        Player player = playerService.getPlayer(securityIdentity.getPrincipal().getName());
        PlayerExportDTO dto = new PlayerExportDTO(player);
        return Templates.quiz(dto);
    }

    @GET
    @RolesAllowed({ADMIN_ROLE,USER_ROLE})
    @Path("{topic}")
    public TemplateInstance startQuiz(@PathParam("topic") String topic) {
        Collection<Quiz> choosedQuizzes = quizService.getQuizzesByTopic(topic);
        Player player = playerService.getPlayer(securityIdentity.getPrincipal().getName());
        PlayerExportDTO dto = new PlayerExportDTO(player);
        return Templates.startquiz(choosedQuizzes,dto);
    }

    /*------------------------------------------- POST --------------------------------------------*/

    @POST
    @RolesAllowed({ADMIN_ROLE,USER_ROLE})
    public TemplateInstance quiz(@FormParam("topic") String topic) {
        Collection<Quiz> searchedQuizzes = quizService.getQuizzesByTopic(topic);
        Player player = playerService.getPlayer(securityIdentity.getPrincipal().getName());
        PlayerExportDTO dto = new PlayerExportDTO(player);
        return Templates.startquiz(searchedQuizzes,dto);
    }

}