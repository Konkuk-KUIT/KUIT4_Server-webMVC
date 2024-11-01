package jwp.model;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;

public class Question {
    private Long questionId;
    private String writer;
    private String title;
    private String contents;
    private Timestamp createdDate;
    private Integer countOfAnswer;

    public Question(Long questionId, String writer, String title, String contents, Timestamp createdDate, Integer countOfAnswer) {
        this.questionId = questionId;
        this.writer = writer;
        this.title = title;
        this.contents = contents;
        this.createdDate = createdDate;
        this.countOfAnswer = countOfAnswer;
    }

    public Question(String writer, String title, String contents) {
        this.writer = writer;
        this.title = title;
        this.contents = contents;
        this.countOfAnswer = 0;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public String getWriter() {
        return writer;
    }

    public String getTitle() {
        return title;
    }

    public String getContents() {
        return contents;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public Integer getCountOfAnswer() {
        return countOfAnswer;
    }

    public void increaseCountOfAnswer() {
        countOfAnswer++;
    }
}
