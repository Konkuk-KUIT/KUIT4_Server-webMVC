package jwp.controller.qna;

import core.mvc.Controller;
import jwp.dao.AnswerDao;
import jwp.dao.QuestionDao;
import jwp.model.Answer;
import jwp.model.Question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class QnaShowController implements Controller {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        QuestionDao questionDao = new QuestionDao();
        int questionId = Integer.parseInt(req.getParameter("questionId"));
        AnswerDao answerDao = new AnswerDao();
        List<Answer> answers = answerDao.read(questionId);
        Question question = questionDao.findByQuestionId(questionId);
        question.setCountOfAnswer(answers.size());
        req.setAttribute("answers", answers);
        req.setAttribute("question", question);

        return "/qna/show.jsp";
    }
}
