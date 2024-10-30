package jwp.controller;

import core.mvc.Controller;
import jwp.dao.QuestionDao;
import jwp.model.Question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ShowController implements Controller {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String questionId = req.getParameter("questionId");

        QuestionDao questionDao = new QuestionDao();
        Question foundQuestion = questionDao.findById(questionId);

        req.setAttribute("question", foundQuestion);
        return "/qna/show.jsp";
    }
}
