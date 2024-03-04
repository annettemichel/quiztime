package hsos.swa.service;

import hsos.swa.boundary.dto.QuizImportDTO;
import hsos.swa.entity.Quiz;
import java.util.Collection;

public interface QuizService {

    Quiz createQuiz(QuizImportDTO quizImportDTO);

    Quiz getQuiz(Long id);

    Collection<Quiz> getAllQuizzes();

    Collection<Quiz> getQuizzesByTopic(String topic);

    void deleteQuiz(Long id);

    Quiz updateQuiz(Long id, String topic, String question, String answer, int points);

}