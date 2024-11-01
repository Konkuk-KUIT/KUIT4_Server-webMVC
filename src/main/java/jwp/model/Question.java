package jwp.model;


import java.sql.Timestamp;

public class Question {
    private String writer;
    private String title;
    private final Timestamp createdDate;        // 유지 못하나.. / 못한대...
    private String contents;
    private int countOfAnswer;

    public Question(String writer, String title, String contents, Timestamp createdDate,  int countOfAnswer) {
        this.writer = writer;
        this.title = title;
        this.contents = contents;
        this.createdDate = createdDate;
        this.countOfAnswer = countOfAnswer;
    }

//    public Question(String writer, String title, String contents, int countOfAnswer) {
//        this.writer = writer;
//        this.title = title;
//        this.createdDate = new Timestamp(System.currentTimeMillis());       // 이거 말고 받아야 될 듯
//        this.contents = contents;
//        this.countOfAnswer = countOfAnswer;
//    }

    public Question(String writer, String title, String contents, Timestamp createdDate) {
        this.writer = writer;
        this.title = title;
        this.createdDate = createdDate;
        this.contents = contents;
        this.countOfAnswer = 0;
    }

    public Question(String writer, String title, String contents) {
        this.writer = writer;
        this.title = title;
        this.createdDate = new Timestamp(System.currentTimeMillis());
        this.contents = contents;
        this.countOfAnswer = 0;
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
