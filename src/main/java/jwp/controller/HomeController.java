package jwp.controller;

import core.db.DBQuestionRepository;
import core.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HomeController implements Controller {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        req.setAttribute("questions", DBQuestionRepository.getInstance().findAll());
        return "/home.jsp";
    }
}
