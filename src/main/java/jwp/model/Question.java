package jwp.model;

import java.sql.Date;
import java.time.LocalDate;

public class Question {
    private final String writer;
    private final Date createdDate;
    private final int countOfAnswer;
    private String title;
    private String contents;
    private int questionId;

    public Question(String writer, String title, String contents, int countOfAnswer) {
        this.writer = writer;
        this.title = title;
        this.contents = contents;
        this.createdDate = Date.valueOf(LocalDate.now());
        this.countOfAnswer = countOfAnswer;
    }

    public Question(String writer, int countOfAnswer, String title, String contents, int questionId) {
        this.writer = writer;
        this.countOfAnswer = countOfAnswer;
        this.title = title;
        this.contents = contents;
        this.questionId = questionId;
        this.createdDate = Date.valueOf(LocalDate.now());
    }

    public Question(String writer, String title, String contents, int countOfAnswer, int questionId) {
        this.writer = writer;
        this.title = title;
        this.contents = contents;
        this.countOfAnswer = countOfAnswer;
        this.createdDate = Date.valueOf(LocalDate.now());
        this.questionId = questionId;
    }

    public int getQuestionId() {
        return questionId;
    }

    public String getWriter() {
        return writer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public int getCountOfAnswer() {
        return countOfAnswer;
    }
}
