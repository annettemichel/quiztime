package hsos.swa.entity;

import javax.persistence.*;
import java.util.List;

@Entity(name = "questions_table")
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "questing_seq")
    @SequenceGenerator(name="question_seq",sequenceName = "question_seq", initialValue = 1)
    private long id;
    @Column
    private String description;
    @OneToMany(mappedBy = "question")
    private List<Selection> selections;
    @Column
    private int points = 0;
    @ManyToOne
    @JoinColumn(name="quiz_id", nullable=false)
    private Quiz quiz;

    public Question(){}

    public Question(String description, List<Selection> selections) {
        this.description = description;
        this.selections = selections;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Selection> getSelections() {
        return selections;
    }

    public void setSelections(List<Selection> selections) {
        this.selections = selections;
    }

    public int getScore() {
        return points;
    }

    public void setScore(int points) {
        this.points = points;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    private Selection getCorrectAnswer(){
        for (Selection selection : selections){
            if(selection.getIsAnswer()==1){
                return selection;
            }
        }
        return null;
    }
}
