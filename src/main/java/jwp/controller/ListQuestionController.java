package jwp.controller;

import core.mvc.Controller;
import jwp.dao.QuestionDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListQuestionController implements Controller {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        QuestionDao questionDao = new QuestionDao();

    }
}
