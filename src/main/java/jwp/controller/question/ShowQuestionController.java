package jwp.controller.question;

import core.mvc.Controller;
import jwp.dao.AnswerDao;
import jwp.dao.QuestionDao;
import jwp.model.Answer;
import jwp.model.Question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowQuestionController implements Controller {

    private final QuestionDao questionDao;
    private final AnswerDao answerDao;

    public ShowQuestionController(QuestionDao questionDao, AnswerDao answerDao) {
        this.questionDao = questionDao;
        this.answerDao = answerDao;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String questionId = req.getParameter("questionId");

        Question question = questionDao.findByQuestionId(Integer.parseInt(questionId));
        req.setAttribute("question", question);

        List<Answer> answers = answerDao.findAllByQuestionId(question.getQuestionId());
        req.setAttribute("answers", answers);

        return "/qna/show.jsp";
    }
}