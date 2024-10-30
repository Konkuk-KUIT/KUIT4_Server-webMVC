package jwp.controller;

import core.mvc.Controller;
import jwp.dao.QuestionDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowController implements Controller {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String questionId = req.getParameter("questionId");

        QuestionDao questionDao = new QuestionDao();
        questionDao.findById(questionId);

        req.setAttribute("question", questionDao.findById(questionId));
        return "/qna/show.jsp";
    }
}
