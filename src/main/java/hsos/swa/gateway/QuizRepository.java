package hsos.swa.gateway;

import hsos.swa.boundary.dto.QuizImportDTO;
import hsos.swa.entity.Quiz;
import hsos.swa.service.QuizService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
@Transactional(Transactional.TxType.REQUIRED)
public class QuizRepository implements QuizService, Serializable {

    @Inject
    EntityManager em;

    /*------------------------------------------- GET --------------------------------------------*/

    @Override
    public Collection<Quiz> getAllQuizzes() {
        TypedQuery<Quiz> query = this.em.createNamedQuery("Quiz.findAll", Quiz.class);
        List<Quiz> resultList = query.getResultList();
        return resultList.stream().collect(Collectors.toList());
    }

    @Override

    public Quiz getQuiz(Long id) {
        return this.em.find(Quiz.class, id);
    }

    @Override
    public Collection<Quiz> getQuizzesByTopic(String topic) {
        TypedQuery<Quiz> query = this.em.createQuery("SELECT quiz FROM quiz_table quiz WHERE quiz.topic =:topic", Quiz.class);
        List<Quiz> resultList = query.setParameter("topic",topic).getResultList();
        return resultList.stream().collect(Collectors.toList());
    }

    /*------------------------------------------- PUT --------------------------------------------*/

    @Override
    public Quiz updateQuiz(Long id, String topic, String question, String answer, int points) {
        TypedQuery<Quiz> query = em.createQuery("SELECT quiz FROM quiz_table quiz WHERE quiz.id =:id", Quiz.class);
        Quiz quiz = query.setParameter("id", id).getSingleResult();
        quiz.setTopic(topic);
        quiz.setQuestion(question);
        quiz.setAnswer(answer);
        quiz.setPoints(points);
        return quiz;
    }

    /*------------------------------------------ POST -------------------------------------------*/

    @Override
    public Quiz createQuiz(QuizImportDTO quizImportDTO) {
        Quiz quiz = new Quiz(quizImportDTO.getTopic(), quizImportDTO.getQuestion(), quizImportDTO.getAnswer(), quizImportDTO.getPoints());
        try {
            em.persist(quiz);
            return quiz;
        } catch (Exception e){
            return null;
        }
    }

    /*----------------------------------------- DELETE ------------------------------------------*/

    @Override
    public void deleteQuiz(Long quizId) {
        Quiz toBeDeletedQuiz = this.em.find(Quiz.class, quizId);
        this.em.remove(toBeDeletedQuiz);
    }

}
