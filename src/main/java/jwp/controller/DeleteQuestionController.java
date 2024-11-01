package jwp.controller;

import core.mvc.Controller;
import jwp.dao.QuestionDao;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteQuestionController implements Controller {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        QuestionDao questionDao = new QuestionDao();

        String questionId = request.getParameter("questionId");
    }
}
