package jwp.controller.qna;

import core.mvc.Controller;
import core.mvc.view.JsonView;
import core.mvc.view.View;
import jwp.dao.AnswerDao;
import jwp.dao.QuestionDao;
import jwp.model.Answer;
import jwp.model.Question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateAnswerController implements Controller {

    private final AnswerDao answerDao = new AnswerDao();
    private final QuestionDao questionDao = new QuestionDao();

    @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        System.out.println("[CreateAnswerController] 실행");
        int questionId = Integer.parseInt(req.getParameter("questionId"));
        Answer answer = new Answer(questionId,
                req.getParameter("writer"),
                req.getParameter("contents"));

        Answer savedAnswer = answerDao.insert(answer);

        Question question = questionDao.findByQuestionId(questionId);
        question.increaseCountOfAnswer();
        questionDao.updateCountOfAnswer(question);

        //  req에 넣어주고 render할때 다시 뽑아서 모델(map)을 반환함
        req.setAttribute("answer", savedAnswer);

        return new JsonView();
    }
}
