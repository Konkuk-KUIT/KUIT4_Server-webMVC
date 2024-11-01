package jwp.controller;

import core.mvc.Controller;
import jwp.dao.QuestionDao;
import jwp.model.Question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class QuestionCreateController implements Controller {

    private final QuestionDao questionDao;

    public QuestionCreateController(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Question question = new Question(req.getParameter("writer"),
                req.getParameter("title"),
                req.getParameter("contents"));
        Question savedQuestion = questionDao.insert(question);
        System.out.println("question 등록 완료, QuestionId : " + savedQuestion.getQuestionId());
        return "redirect:/";
    }

}
