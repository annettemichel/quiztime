package hsos.swa.boundary.rest;

import hsos.swa.boundary.dto.QuizExportDTO;
import hsos.swa.service.QuizService;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/api/quizzes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class QuizzesResource {

    @Inject
    QuizService quizService;
    @Inject
    Logger logger;

    /*------------------------------------------- GET --------------------------------------------*/

    @GET
    @Operation(summary = "Return quizzes", description = "Returns all quizzes")
    @Timeout
    @Retry
    public Response getAllQuizzes() {
        logger.info("Returns all quizzes");
        List<QuizExportDTO> quizzes = quizService.getAllQuizzes()
                .stream()
                .map(quiz -> new QuizExportDTO(quiz))
                .toList();
        if (!quizzes.isEmpty()) {
            return Response.ok(quizzes).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @GET
    @Path("{topic}")
    @Operation(summary = "Return quizzes by topic", description = "Returns all quizzes by choosen topic")
    @Parameter(name = "topic", description = "Quiz topic", required = true)
    @Timeout
    @Retry
    public Response getAllQuizzesByTopic(@PathParam("topic") String topic) {
        logger.info("Returns all quizzes by choosen Topic");
        List<QuizExportDTO> quizzes = quizService.getQuizzesByTopic(topic)
                .stream()
                .map(quiz -> new QuizExportDTO(quiz))
                .toList();
        if (!quizzes.isEmpty()) {
            return Response.ok(quizzes).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

}
