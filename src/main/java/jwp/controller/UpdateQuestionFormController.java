package jwp.controller;

import core.mvc.Controller;
import jwp.dao.QuestionDao;
import jwp.model.Question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class UpdateQuestionFormController implements Controller {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) throws SQLException {
        QuestionDao questionDao = new QuestionDao();

        int questionId = Integer.parseInt(req.getParameter("questionId"));
        Question quesiton = questionDao.findById(questionId);

        req.setAttribute("questionId", questionId);
        req.setAttribute("question", quesiton);

        return "/qna/updateForm.jsp";
    }
}
