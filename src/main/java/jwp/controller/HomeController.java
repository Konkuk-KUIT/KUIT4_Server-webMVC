package jwp.controller;

import core.mvc.Controller;
import jwp.dao.AnswerDao;
import jwp.dao.QuestionDao;
import jwp.model.Question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

public class HomeController implements Controller {

    private final QuestionDao questionDao;
    private final AnswerDao answerDao;

    public HomeController(QuestionDao questionDao, AnswerDao answerDao) {
        this.questionDao = questionDao;
        this.answerDao = answerDao;
        initCountOfAnswer();
    }

    private void initCountOfAnswer() {
        try {
            List<Question> questions = questionDao.findAll();
            for (Question question : questions) {
                int countOfAnswer = answerDao.findAllByQuestionId(question.getQuestionId()).size();
                question.updateCountOfAnswer(countOfAnswer);
                questionDao.update(question);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        req.setAttribute("questions", questionDao.findAll());
        return "/home.jsp";
    }
}
