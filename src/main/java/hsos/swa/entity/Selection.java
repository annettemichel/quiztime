package hsos.swa.entity;

import javax.persistence.*;

@Entity(name = "selections_table")
public class Selection {

    @Id
    private Long id;
    @Column
    private String selectionText;
    @Column
    private int isAnswer;
    @ManyToOne
    @JoinColumn(name = "question_id",nullable = false)
    private Question question;

    public Selection() {
    }

    public Selection(String selectionText, int isAnswer) {
        this.selectionText = selectionText;
        this.isAnswer = isAnswer;
    }

    public int getIsAnswer() {
        return isAnswer;
    }

    public void setIsAnswer(int isAnswer) {
        this.isAnswer = isAnswer;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSelectionText() {
        return selectionText;
    }

    public void setSelectionText(String selectionText) {
        this.selectionText = selectionText;
    }

}
