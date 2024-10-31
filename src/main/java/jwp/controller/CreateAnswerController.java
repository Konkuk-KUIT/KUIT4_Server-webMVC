package jwp.controller;
import com.fasterxml.jackson.databind.ObjectMapper;
import core.mvc.Controller;
import jwp.dao.AnswerDao;
import jwp.dao.QuestionDao;
import jwp.model.Answer;
import jwp.model.Question;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
public class CreateAnswerController implements Controller {
    private final AnswerDao answerDao = new AnswerDao();
    private final QuestionDao questionDao = new QuestionDao();
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        System.out.println("[CreateAnswerController] 실행");
        int questionId = Integer.parseInt(req.getParameter("questionId"));
        Answer answer = new Answer(questionId,
                req.getParameter("writer"),
                req.getParameter("contents"));
        Answer savedAnswer = answerDao.insert(answer);
        Question question = questionDao.findByQuestionId(questionId);
        question.increaseCountOfAnswer();
        questionDao.updateCountOfAnswer(question);
        ObjectMapper objectMapper = new ObjectMapper();
        resp.setContentType("application/json;charset=utf-8");
        PrintWriter out = resp.getWriter();
        out.print(objectMapper.writeValueAsString(savedAnswer));
        return null;
    }
}