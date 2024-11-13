package jwp.controller;

import core.mvc.Controller;
import jwp.dao.QuestionDao;
import jwp.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

public class DeleteQuestionController implements Controller {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) throws SQLException {
        HttpSession session = req.getSession();
        QuestionDao questionDao = new QuestionDao();

        int questionId = Integer.parseInt(req.getParameter("questionId"));
        req.setAttribute("questionId", questionId);

        User user = (User) session.getAttribute("user");
        String writer = questionDao.findById(questionId).getWriter();

        if (writer == user.getUserId()) {
            questionDao.delete(questionId);
        }
        return "redirect:/";
    }
}
