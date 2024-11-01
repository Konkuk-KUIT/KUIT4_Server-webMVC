package jwp.controller;

import core.mvc.Controller;
import jwp.dao.QuestionDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateQuestionController implements Controller {
    private final QuestionDao questionDao = new QuestionDao();
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        return "redirect:/";
    }
}
