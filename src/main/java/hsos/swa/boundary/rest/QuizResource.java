package hsos.swa.boundary.rest;

import hsos.swa.boundary.dto.QuizImportDTO;
import hsos.swa.entity.Quiz;
import hsos.swa.service.QuizService;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameters;
import org.jboss.logging.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.inject.Inject;
import javax.ws.rs.core.Response;

@Path("/api/quiz")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class QuizResource {

    @Inject
    QuizService service;
    @Inject
    Logger logger;

    /*------------------------------------------- GET --------------------------------------------*/

    @GET
    @Path("{id}")
    @Operation(summary = "Returns quiz by Id", description = "Returns a single quiz with id")
    @Parameter(name = "id", description = "Quiz Id", required = true)
    @Timeout
    @Retry
    public Response getQuiz(@PathParam("id") Long id) {
        logger.info("Returns a single quiz with id " + id);
        return Response.ok().entity(service.getQuiz(id)).build();
    }

    /*------------------------------------------- PUT --------------------------------------------*/

    @PUT
    @Path("{id}")
    @Operation(summary = "Edit quiz by Id", description = "Edit quiz with Id")
    @Parameters({
            @Parameter(name = "id", description = "Quiz Id", required = true),
            @Parameter(name = "topic", description = "Quiz topic", required = true),
            @Parameter(name = "question", description = "Quiz question", required = true),
            @Parameter(name = "answer", description = "Quiz answer", required = true),
            @Parameter(name = "points", description = "Quiz points", required = true)
    })
    @Timeout
    @Retry
    public Response updateQuizInformation(@PathParam("id") Long id, @QueryParam("topic") String topic, @QueryParam("question") String question, @QueryParam("answer") String answer, @QueryParam("points") int points) {
        logger.info("Edit quiz with Id: " + id);
        Quiz updatedQuiz = service.updateQuiz(id, topic, question, answer, points);
        return Response.ok().entity(updatedQuiz).build();
    }

    /*------------------------------------------- POST -------------------------------------------*/

    @POST
    @Operation(summary = "Create quiz", description = "Create new quiz for topic")
    @Parameters({
            @Parameter(name = "topic", description = "Quiz topic", required = true),
            @Parameter(name = "question", description = "Quiz question", required = true),
            @Parameter(name = "answer", description = "Quiz answer", required = true),
            @Parameter(name = "points", description = "Quiz points", required = true)
    })
    public Response createQuiz(@QueryParam("topic") String topic, @QueryParam("question") String question, @QueryParam("answer") String answer, @QueryParam("points") int points) {
        logger.info("Create new quiz for " + topic + "-topic");
        QuizImportDTO quizImportDTO = new QuizImportDTO(topic, question, answer, points);
        Quiz newquiz = service.createQuiz(quizImportDTO);
        return Response.status(Response.Status.CREATED).entity(newquiz).build();
    }

    /*------------------------------------------ DELETE ------------------------------------------*/

    @DELETE
    @Path("{quizId}")
    @Operation(summary = "Delete quiz", description = "Delete quiz with id")
    @Parameter(name = "id", description = "Quiz Id", required = true)
    @Timeout
    @Retry
    public void deleteQuiz(@PathParam("quizId") Long quizId) {
        logger.info("Delete quiz with id: " + quizId);
        service.deleteQuiz(quizId);
    }

}
