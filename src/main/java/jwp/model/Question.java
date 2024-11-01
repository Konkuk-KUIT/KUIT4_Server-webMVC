package jwp.model;


import java.sql.Date;
import java.time.LocalDate;

public class Question {
    private long questionId;
    private String writer;
    private String title;
    private String contents;
    private Date createdDate;
    private int countOfAnswer;

    public Question(long questionId, String writer, String title, String contents, Date createdDate, int countOfAnswer){
        this.questionId = questionId;
        this.writer = writer;
        this.title = title;
        this.contents = contents;
        this.createdDate = createdDate;
        this.countOfAnswer = countOfAnswer;
    }
    public Question(String writer, String title, String contents, int countOfAnswer) {
        this.questionId = 0;
        this.writer = writer;
        this.title = title;
        this.contents = contents;
        this.createdDate = Date.valueOf(LocalDate.now());
        this.countOfAnswer = countOfAnswer;
    }

    public long getQuestionId(){return questionId;}
    public String getWriter(){return writer;}
    public String getTitle(){return title;}
    public String getContents(){return contents;}
    public Date getCreatedDate(){return createdDate;}
    public int getCountOfAnswer(){return countOfAnswer;}
}
