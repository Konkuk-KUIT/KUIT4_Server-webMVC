package jwp.controller.qna;

import core.mvc.AbstractController;
import core.mvc.view.ModelAndView;
import jwp.dao.AnswerDao;
import jwp.dao.QuestionDao;
import jwp.model.Answer;
import jwp.model.Question;

import java.util.Map;

public class CreateAnswerController extends AbstractController {

    private final AnswerDao answerDao = new AnswerDao();
    private final QuestionDao questionDao = new QuestionDao();

    @Override
    public ModelAndView execute(Map<String, String> params) throws Exception {
        int questionId = Integer.parseInt(params.get("questionId"));

        Answer answer = new Answer(questionId,
                params.get("writer"),
                params.get("contents"));

        Answer savedAnswer = answerDao.insert(answer);
        Question question = questionDao.findByQuestionId(questionId);
        question.increaseCountOfAnswer();
        questionDao.updateCountOfAnswer(question);

        return jsonView().addObject("answer", savedAnswer);
    }
}
