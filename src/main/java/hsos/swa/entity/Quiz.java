package hsos.swa.entity;

import javax.persistence.*;

@Entity(name = "quiz_table")
@NamedQuery(name = "Quiz.findAll", query = "SELECT quiz FROM quiz_table quiz ORDER BY quiz.topic")
@NamedQuery(name = "Quiz.findQuizzesByTopic", query = "SELECT quiz FROM quiz_table quiz WHERE quiz.topic =:topic")
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "quiz_seq")
    @SequenceGenerator(name="quiz_seq", sequenceName = "quiz_seq",allocationSize = 1)
    private Long id;
    private String topic;
    @Column(unique = true)
    private String question;
    private String answer;
    private int points;

    public Quiz() {
    }

    public Quiz(String topic, String question, String answer,int points) {
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
}