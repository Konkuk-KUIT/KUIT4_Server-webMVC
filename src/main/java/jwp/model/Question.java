package jwp.model;

import java.sql.Timestamp;

public class Question {
    private long questionId;
    private String writer;
    private String title;
    private String contents;
    private Timestamp createdDate;
    private int countOfAnswer;

    public Question(long questionId, String writer, String title, String contents, Timestamp createdDate, int countOfAnswer) {
        this.questionId = questionId;
        this.writer = writer;
        this.title = title;
        this.contents = contents;
        this.createdDate = createdDate;
        this.countOfAnswer = countOfAnswer;
    }

    public Question(String writer, String title, String contents, Timestamp createdDate, int countOfAnswer) {
        this.writer = writer;
        this.title = title;
        this.contents = contents;
        this.createdDate = createdDate;
        this.countOfAnswer = countOfAnswer;
    }

    public long getQuestionId() { return questionId; }
    public String getWriter() { return writer; }
    public String getTitle() { return title; }
    public String getContents() { return contents; }
    public Timestamp getCreatedDate() { return createdDate; }
    public int getCountOfAnswer() { return countOfAnswer; }

    public void update(Question updateQuestion) {
        this.title = updateQuestion.title;
        this.contents = updateQuestion.contents;
    }


}
