package jwp.model;

import java.sql.Timestamp;

public class Question {
    private Long questionId;
    private String writer;
    private String title;
    private String contents;
    private Timestamp createdDate;
    private int countOfAnswer;


    public Question(Long questionId, String writer, String title, String contents, Timestamp createdDate, int countOfAnswer) {
        this.questionId = questionId;
        this.writer = writer;
        this.title = title;
        this.contents = contents;
        this.createdDate = createdDate;
        this.countOfAnswer = countOfAnswer;
    }

    public Question(String writer, String title, String contents, Timestamp createdDate, int countOfAnswer) {
        this.questionId = 0L;
        this.writer = writer;
        this.title = title;
        this.contents = contents;
        this.createdDate = createdDate;
        this.countOfAnswer = countOfAnswer;
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

    public int getCountOfAnswer() {
        return countOfAnswer;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void increaseCountOfAnswer() {
        this.countOfAnswer++;
    }
}


//CREATE TABLE QUESTIONS (
//      questionId 			bigint				auto_increment,
//      writer				varchar(30)			NOT NULL,
//      title				varchar(50)			NOT NULL,
//      contents			varchar(5000)		NOT NULL,
//      createdDate			timestamp			NOT NULL,
//      countOfAnswer int,
//      PRIMARY KEY               (questionId)
//);