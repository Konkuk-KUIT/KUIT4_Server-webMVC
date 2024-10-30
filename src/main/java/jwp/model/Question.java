package jwp.model;

import java.sql.Timestamp;

public class Question {
    private String writer;
    private String title;
    private Timestamp createdDate;
    private String contents;
    private int countOfAnswer;

    public Question(String writer, String title, Timestamp createdDate, String contents, int countOfAnswer) {
        this.writer = writer;
        this.title = title;
        this.createdDate = createdDate;
        this.contents = contents;
        this.countOfAnswer = countOfAnswer;
    }

    public Question(String writer, String title, String contents, int countOfAnswer) {
        this.writer = writer;
        this.title = title;
        this.createdDate = new Timestamp(System.currentTimeMillis());
        this.contents = contents;
        this.countOfAnswer = countOfAnswer;
    }

//    public int getQuestionId() {
//        return questionId;
//    }

    public String getWriter() {
        return writer;
    }

    public String getTitle() {
        return title;
    }

    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public String getContents() {
        return contents;
    }

    public int getCountOfAnswer() {
        return countOfAnswer;
    }

    public void modifyQuestion(Question question) {
        this.title = question.getTitle();
        this.contents = question.getContents();
    }
}
