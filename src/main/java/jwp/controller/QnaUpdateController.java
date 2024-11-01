package jwp.controller;

import core.mvc.Controller;
import jwp.dao.QuestionDao;
import jwp.model.Question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class QnaUpdateController implements Controller {

    private final QuestionDao questionDao = new QuestionDao();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        QuestionDao questionDao = new QuestionDao();
        Question question = questionDao.findByQuestionId(Integer.parseInt(req.getParameter("questionId")));

        question.setTitle(req.getParameter("title"));
        question.setContents(req.getParameter("contents"));

        questionDao.update(question);

        return "redirect:/";
    }
}
