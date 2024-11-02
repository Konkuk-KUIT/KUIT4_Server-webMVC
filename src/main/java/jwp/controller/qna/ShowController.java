package jwp.controller.qna;

import core.mvc.Controller;
import core.mvc.view.JspView;
import core.mvc.view.View;
import jwp.dao.AnswerDao;
import jwp.dao.QuestionDao;
import jwp.model.Answer;
import jwp.model.Question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowController implements Controller {
    private final QuestionDao questionDao = new QuestionDao();
    private final AnswerDao answerDao = new AnswerDao();

    @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        int questionId = Integer.parseInt(req.getParameter("questionId"));

        Question question = questionDao.findByQuestionId(questionId);
        List<Answer> answers = answerDao.findAll(questionId);

        req.setAttribute("question", question);
        req.setAttribute("answers", answers);

        return new JspView("/qna/show.jsp");
    }
}