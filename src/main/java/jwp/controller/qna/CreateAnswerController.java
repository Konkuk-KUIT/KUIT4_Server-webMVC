package jwp.controller.qna;

import com.fasterxml.jackson.databind.ObjectMapper;
import core.mvc.AbstractController;
import core.mvc.Controller;
import core.mvc.view.JsonView;
import core.mvc.view.ModelAndView;
import core.mvc.view.View;
import jwp.dao.AnswerDao;
import jwp.dao.QuestionDao;
import jwp.model.Answer;
import jwp.model.Question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.sql.Savepoint;

public class CreateAnswerController extends AbstractController {

    private final AnswerDao answerDao = new AnswerDao();
    private final QuestionDao questionDao = new QuestionDao();

    @Override
    public ModelAndView execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        System.out.println("[CreateAnswerController] 실행");
        int questionId = Integer.parseInt(req.getParameter("questionId"));
        Answer answer = new Answer(questionId,
                req.getParameter("writer"),
                req.getParameter("contents"));

        Answer savedAnswer = answerDao.insert(answer);

        Question question = questionDao.findByQuestionId(questionId);
        question.increaseCountOfAnswer();
        questionDao.updateCountOfAnswer(question);

        //addobj!
        return jsonView().addObject("answer", savedAnswer);
    }
}
