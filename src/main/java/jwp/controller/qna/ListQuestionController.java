package jwp.controller.qna;

import core.mvc.Controller;
import jwp.dao.QuestionDao;
import jwp.model.Question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

public class ListQuestionController implements Controller {

    private final QuestionDao questionDao = new QuestionDao();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
        List<Question> questions = questionDao.showQuestionList();
        // questions라는 key로 home.jsp에 전달
        req.setAttribute("questions", questions);
        return "/home.jsp";
    }
}
