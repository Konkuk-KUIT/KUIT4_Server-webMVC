package jwp.controller;

import core.mvc.Controller;
import jwp.dao.QuestionDao;
import jwp.model.Question;
import jwp.model.User;
import org.apache.catalina.Session;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ShowController implements Controller {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        QuestionDao questionDao = new QuestionDao();
        HttpSession session = req.getSession();

        int questionId = Integer.parseInt(req.getParameter("questionId"));
        Question question = questionDao.findById(questionId);

        req.setAttribute("questionId", questionId);
        req.setAttribute("question", question);

        User user = (User) session.getAttribute("user");

        String writer = null;
        if (user != null) {
            writer = questionDao.findById(questionId).getWriter();
        }

        req.setAttribute("isMine", false);
        if (writer != null && writer == user.getUserId()) {
            req.setAttribute("isMine", true);
        }

        return "/qna/show.jsp";
    }
}