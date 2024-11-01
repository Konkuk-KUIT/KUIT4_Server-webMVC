package jwp.controller.question;

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
    private final QuestionDao questionDao;
    private final AnswerDao answerDao;

    public CreateAnswerController(QuestionDao questionDao, AnswerDao answerDao) {
        this.questionDao = questionDao;
        this.answerDao = answerDao;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        int questionId = Integer.parseInt(req.getParameter("questionId"));

        Answer answer = new Answer(req.getParameter("writer"),
                req.getParameter("contents"),
                questionId);

        Answer savedAnswer = answerDao.insert(answer);
        System.out.println("answer 등록 완료, AnswerId : " + savedAnswer.getAnswerId());

        Question question = questionDao.findByQuestionId(questionId);
        question.increaseCountOfAnswer();
        questionDao.update(question);

        // 객체를 json화
        ObjectMapper objectMapper = new ObjectMapper();
        resp.setContentType("application/json;charset=utf-8");
        PrintWriter out = resp.getWriter();
        out.print(objectMapper.writeValueAsString(savedAnswer));

        return null;
    }
}
