package jwp.model;

public class Question {
    private String writer;
    private String title;
    private String contents;
    private String createDate;
    private int countOfAnswer;

    public Question(String writer, String title, String contents, String createDate, int countOfAnswer) {
        this.writer = writer;
        this.title = title;
        this.contents = contents;
        this.createDate = createDate;
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

    public String getCreateDate() {
        return createDate;
    }

    public int getCountOfAnswer() {
        return countOfAnswer;
    }

}
