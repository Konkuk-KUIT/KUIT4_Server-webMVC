package Controller.qna;

import Controller.Controller;
import jwp.dao.AnswerDao;
import jwp.dao.QuestionDao;
import jwp.model.Answer;
import jwp.model.Question;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class QnaShowController implements Controller {

    private final QuestionDao questionDao = new QuestionDao();
    private final AnswerDao answerDao = new AnswerDao();

    @Override
    public String handleRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {

        Long questionId = Long.valueOf(req.getParameter("questionId"));
        Question question = questionDao.findByQuestionId(questionId);
        List<Answer> answers = answerDao.findByQuestionId(questionId);
        req.setAttribute("question", question);
        req.setAttribute("answers", answers);

        return "/qna/show.jsp";
    }
}