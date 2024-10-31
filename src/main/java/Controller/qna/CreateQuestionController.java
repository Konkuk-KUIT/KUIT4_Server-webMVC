package Controller.qna;

import Controller.Controller;
import jwp.dao.QuestionDao;
import jwp.model.Question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;

public class CreateQuestionController implements Controller {

    private final QuestionDao questionDao = new QuestionDao();

    @Override
    public String handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String writer = req.getParameter("writer");
        String title = req.getParameter("title");
        String contents = req.getParameter("contents");

        Timestamp createdDate = new Timestamp(System.currentTimeMillis());

        Question question = new Question(null, writer, title, contents, createdDate, 0);
        Question savedQuestion = questionDao.insert(question);

        req.setAttribute("question", savedQuestion);
        return "/qna/show.jsp";  // show.jsp로 forward
    }
}
