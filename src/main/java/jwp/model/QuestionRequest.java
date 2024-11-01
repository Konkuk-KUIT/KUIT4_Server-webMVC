package jwp.model;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;

public class QuestionRequest {

    private String writer;
    private String title;
    private String contents;
    private Timestamp createdDate;
    private int countOfAnswer;

    public QuestionRequest(String writer, String title, String contents, int countOfAnswer) {
        this.writer = writer;
        this.title = title;
        this.contents = contents;
        this.createdDate =  new Timestamp(System.currentTimeMillis());
        this.countOfAnswer = countOfAnswer;
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

    public int getCountOfAnswer() {
        return countOfAnswer;
    }
}
