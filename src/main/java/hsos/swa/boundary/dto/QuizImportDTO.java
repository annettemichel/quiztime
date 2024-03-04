package hsos.swa.boundary.dto;

import javax.enterprise.inject.Vetoed;

@Vetoed
public class QuizImportDTO {
    private Long id;
    private String topic;
    private String question;
    private String answer;
    private int points;

    public QuizImportDTO(String topic, String question, String answer, int points) {
        this.topic = topic;
        this.question = question;
        this.answer = answer;
        this.points = points;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    @Override
    public String toString() {
        return "QuizImportDTO{" +
                "topic='" + topic + '\'' +
                ", question='" + question + '\'' +
                ", answer='" + answer + '\'' +
                ", points=" + points +
                '}';
    }
}
