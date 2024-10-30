package jwp.controller;

import core.mvc.Controller;
import jwp.dao.QuestionDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HomeController implements Controller {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        QuestionDao questionDao = new QuestionDao();
        req.setAttribute("questions", questionDao.getAllQuestion());

        return "/home.jsp";
    }
}
