package jwp.controller;

import core.mvc.Controller;
import jwp.dao.AnswerDao;
import jwp.dao.QuestionDao;
import jwp.model.Answer;
import jwp.model.Question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowController implements Controller {

    QuestionDao questionDao = new QuestionDao();
    AnswerDao answerDao = new AnswerDao();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Long questionId = Long.parseLong(req.getParameter("questionId"));

        Question findQuestion = questionDao.findByQuestionId(questionId);

        List<Answer> answers = answerDao.findAnswersByQuestionId(findQuestion.getQuestionId());

        req.setAttribute("question", findQuestion);

        req.setAttribute("answers", answers);

        return "/qna/show.jsp";
    }
}
