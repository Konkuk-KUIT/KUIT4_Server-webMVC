package Controller.qna;

import Controller.Controller;
import jwp.dao.AnswerDao;
import jwp.model.Answer;
import jwp.model.Question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;

public class CreateAnswerController implements Controller {

    private final AnswerDao answerDao = new AnswerDao();

    @Override
    public String handleRequest(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        Long questionId = Long.parseLong(req.getParameter("questionId"));
        String writer = req.getParameter("writer");
        String contents = req.getParameter("contents");

        Timestamp createdDate = new Timestamp(System.currentTimeMillis());

        Answer answer = new Answer(null, writer, contents, createdDate, questionId);
        Answer savedAnswer = answerDao.insert(answer);
        req.setAttribute("answer", savedAnswer);
        return "redirect:/qna/show?questionId=" + questionId;
    }
}