package jwp.model;

import java.sql.Timestamp;

public class Answer {
    private long answerId;
    private String writer;
    private String contents;
    private Timestamp createdDate;
    private long questionId;

    public Answer(Long answerId, String writer, String contents, Timestamp createdDate, Long questionId) {
        this.answerId = answerId;
        this.writer = writer;
        this.contents = contents;
        this.createdDate = createdDate;
        this.questionId = questionId;
    }

    public long getAnswerId() {
        return answerId;
    }

    public String getWriter() {
        return writer;
    }

    public String getContents() {
        return contents;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public long getQuestionId() {
        return questionId;
    }
}
