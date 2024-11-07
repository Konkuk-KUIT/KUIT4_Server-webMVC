package jwp.controller.qna;

import core.mvc.controller.ControllerV2;
import jwp.dao.AnswerDao;
import jwp.dao.QuestionDao;
import jwp.model.Answer;
import jwp.model.Question;

import java.sql.SQLException;
import java.util.Map;

import static core.mvc.ViewResolver.JSON_VIEW_PREFIX;

public class CreateAnswerControllerV2 implements ControllerV2 {

    private final AnswerDao answerDao = new AnswerDao();
    private final QuestionDao questionDao = new QuestionDao();

    @Override
    public String execute(Map<String, String> params, Map<String, Object> model) throws SQLException {
        int questionId = Integer.parseInt(params.get("questionId"));
        Answer answer = new Answer(questionId,
                params.get("writer"),
                params.get("contents"));

        Answer savedAnswer = answerDao.insert(answer);
        Question question = questionDao.findByQuestionId(questionId);
        question.increaseCountOfAnswer();
        questionDao.updateCountOfAnswer(question);

        model.put("answer", savedAnswer);
        return JSON_VIEW_PREFIX;
    }
}
