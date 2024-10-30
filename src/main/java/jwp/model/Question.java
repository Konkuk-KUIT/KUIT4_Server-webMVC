package jwp.model;

import java.util.Date;

public class Question {
    private int questionId;
    private String writer;
    private String title;
    private String contents;
    private Date createdDate;
    private int countOfAnswer;

    public Question(int questionId, String writer, String title, String contents, Date createdDate, int countOfAnswer) {
        this.questionId = questionId;
        this.writer = writer;
        this.title = title;
        this.contents = contents;
        this.createdDate = createdDate;
        this.countOfAnswer = countOfAnswer;
    }

    public int getCountOfAnswer() {
        return countOfAnswer;
    }

    public String getCreatedDate() {
        String[] parts = createdDate.toString().split(":");
        return parts[0] + ":" + parts[1];
    }

    public String getContents() {
        return contents;
    }

    public String getTitle() {
        return title;
    }

    public String getWriter() {
        return writer;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public void setCountOfAnswer(int countOfAnswer) {
        this.countOfAnswer = countOfAnswer;
    }
}

//questionId 			bigint				auto_increment,
//writer				varchar(30)			NOT NULL,
//title				varchar(50)			NOT NULL,
//contents			varchar(5000)		NOT NULL,
//createdDate			timestamp			NOT NULL,
//countOfAnswer int,
//PRIMARY KEY               (questionId)