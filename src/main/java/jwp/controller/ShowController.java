package jwp.controller;

import core.mvc.Controller;
import jwp.dao.AnswerDao;
import jwp.dao.QuestionDao;
import jwp.model.Answer;
import jwp.model.Question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

public class ShowController implements Controller {
    private final QuestionDao questionDao = new QuestionDao();
    private final AnswerDao answerDao = new AnswerDao();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        int questionId = Integer.parseInt(req.getParameter("questionId"));

        try {
            Question question = questionDao.findByQuestionId(questionId);
            List<Answer> answers = answerDao.findAll(questionId);

            req.setAttribute("question", question);
            req.setAttribute("answers", answers);

            return "/qna/show.jsp";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}