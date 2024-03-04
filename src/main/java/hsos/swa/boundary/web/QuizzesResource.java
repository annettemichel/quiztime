package hsos.swa.boundary.web;

import hsos.swa.boundary.dto.QuizImportDTO;
import hsos.swa.entity.Quiz;
import hsos.swa.service.PlayerService;
import hsos.swa.service.QuizService;
import io.quarkus.qute.CheckedTemplate;
import io.quarkus.qute.TemplateInstance;

import javax.annotation.security.RolesAllowed;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collection;
import java.util.List;

@Path("/quizzes")
@ApplicationScoped
@Produces(MediaType.TEXT_HTML)
@Transactional
public class QuizzesResource {

    @Inject
    QuizService quizService;
    @Inject
    PlayerService playerService;

    final static String ADMIN_ROLE = "admin";
    final static String USER_ROLE = "user";

    /*------------------------------------------- TEMPLATES --------------------------------------------*/

    @CheckedTemplate
    public static class Templates {
        public static native TemplateInstance quizzes(Collection<Quiz> quizzes);
        public static native TemplateInstance quizdetail(Quiz quiz);
        public static native TemplateInstance quizsearch(Quiz quiz);

    }

    /*------------------------------------------- GET --------------------------------------------*/

    @GET
    @RolesAllowed(ADMIN_ROLE)
    public TemplateInstance getAllQuizzes() {
        return Templates.quizzes(quizService.getAllQuizzes());
    }

    @GET
    @RolesAllowed(ADMIN_ROLE)
    @Path("{id}")
    public TemplateInstance getQuizDetail(@PathParam("id") Long id) {
        return Templates.quizdetail(quizService.getQuiz(id));
    }

    /*------------------------------------------- PUT --------------------------------------------*/

    @POST
    @RolesAllowed(ADMIN_ROLE)
    @Path("/{id}/update")
    public TemplateInstance updateQuiz(@PathParam("id") Long id, @FormParam("topic") String topic, @FormParam("question") String question, @FormParam("answer") String answer, @FormParam("points") String points) {
        quizService.updateQuiz(id, topic, question, answer, Integer.parseInt(points));
        return Templates.quizdetail(quizService.getQuiz(id));
    }

    /*------------------------------------------- POST -------------------------------------------*/

    @POST
    @RolesAllowed(ADMIN_ROLE)
    public TemplateInstance createQuiz(@FormParam("topic") String topic, @FormParam("question") String question, @FormParam("answer") String answer, @FormParam("points") int points) {
        QuizImportDTO quizImportDTO = new QuizImportDTO(topic,question,answer,points);
        quizService.createQuiz(quizImportDTO);
        //quizService.createQuiz(topic, question, answer, Integer.parseInt(points));
        return Templates.quizzes(quizService.getAllQuizzes());
    }


    @POST
    @RolesAllowed(ADMIN_ROLE)
    @Path("/searchid")
    public TemplateInstance searchQuiz(@FormParam("id") Long id) {
        Quiz searchedQuiz = quizService.getQuiz(id);
        return Templates.quizsearch(searchedQuiz);
    }

    @POST
    @RolesAllowed(ADMIN_ROLE)
    @Path("/searchtopic")
    public TemplateInstance getQuizzesByTopic(@FormParam("topic") String topic) {
        Collection<Quiz> searchedQuizzes = quizService.getQuizzesByTopic(topic);
        return Templates.quizzes(searchedQuizzes);
    }

    @POST
    @RolesAllowed(ADMIN_ROLE)
    @Path("/delete/{quizId}")
    public TemplateInstance deletePlayer(@PathParam("quizId") Long quizId) {
        quizService.deleteQuiz(quizId);
        return Templates.quizzes(quizService.getAllQuizzes());
    }

    @POST
    @RolesAllowed(ADMIN_ROLE)
    @Path("/{quizId}/delete")
    public TemplateInstance deletePlayerDetail(@PathParam("quizId") Long quizId) {
        quizService.deleteQuiz(quizId);
        return Templates.quizzes(quizService.getAllQuizzes());
    }

}
