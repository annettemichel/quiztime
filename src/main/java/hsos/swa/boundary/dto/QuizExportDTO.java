package hsos.swa.boundary.dto;

import hsos.swa.entity.Quiz;

import javax.enterprise.inject.Vetoed;
import javax.persistence.Column;

@Vetoed
public class QuizExportDTO {
    private Long id;
    private String topic;
    private String question;
    private String answer;
    private int points;

    public QuizExportDTO(Quiz quiz){
        this.id = quiz.getId();
        this.topic = quiz.getTopic();
        this.question = quiz.getQuestion();
        this.answer = quiz.getAnswer();
        this.points = quiz.getPoints();
    }

    @Override
    public String toString() {
        return "QuizExportDTO{" +
                "id=" + id +
                ", topic='" + topic + '\'' +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                ", points=" + points +
                '}';
    }

    public Long getId() {
        return id;
    }

    public String getTopic() {
        return topic;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public int getPoints() {
        return points;
    }
}
