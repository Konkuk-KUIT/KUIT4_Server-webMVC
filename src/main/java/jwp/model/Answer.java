package jwp.model;

import java.sql.Date;
import java.time.LocalDate;

public class Answer {
    private final int questionId;
    private final String writer;
    private final String contents;
    private final Date createdDate;
    private int answerId;

    public Answer(int questionId, String writer, String contents) {
        this.questionId = questionId;
        this.writer = writer;
        this.contents = contents;
        this.createdDate = Date.valueOf(LocalDate.now());
    }

    public Answer(int questionId, String writer, String contents, Date createdDate, int answerId) {
        this.questionId = questionId;
        this.writer = writer;
        this.contents = contents;
        this.createdDate = createdDate;
        this.answerId = answerId;
    }

    public Answer(int answerId, int questionId, String writer, String contents) {
        this.answerId = answerId;
        this.questionId = questionId;
        this.writer = writer;
        this.contents = contents;
        this.createdDate = Date.valueOf(LocalDate.now());
    }

    public void setquestionId(int questionId) {
        this.answerId = questionId;
    }

    public int getQuestionId() {
        return questionId;
    }

    public String getWriter() {
        return writer;
    }

    public String getContents() {
        return contents;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public int getAnswerId() {
        return answerId;
    }
}
