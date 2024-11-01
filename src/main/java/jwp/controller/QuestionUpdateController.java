package jwp.controller;

import core.mvc.Controller;
import jwp.dao.QuestionDao;
import jwp.model.Question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class QuestionUpdateController implements Controller {

    private final QuestionDao questionDao = new QuestionDao();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String questionId = req.getParameter("questionId");
        Question question = questionDao.findByQuestionId(Integer.parseInt(questionId));

        question.updateTitleAndContents(req.getParameter("title"),
                req.getParameter("contents"));
        questionDao.update(question);
        return "redirect:/";
    }
}