package jwp.controller;

import core.mvc.Controller;
import jwp.dao.AnswerDao;
import jwp.dao.QuestionDao;
import jwp.model.Answer;
import jwp.model.Question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ShowController implements Controller {

    private final AnswerDao answerDao = new AnswerDao();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String questionId = req.getParameter("questionId");

        QuestionDao questionDao = new QuestionDao();
        Question foundQuestion = questionDao.findById(questionId);

        List<Answer> answers = answerDao.findAllByQuestionId(foundQuestion.getQuestionId());

        req.setAttribute("question", foundQuestion);
        req.setAttribute("answers", answers);
        return "/qna/show.jsp";
    }
}
