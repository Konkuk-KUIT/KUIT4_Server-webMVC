package jwp.controller.qna;

import core.mvc.controller.ControllerV2;
import jwp.dao.QuestionDao;
import jwp.model.Question;

import java.sql.SQLException;
import java.util.Map;

public class CreateQuestionControllerV2 implements ControllerV2 {

    private final QuestionDao questionDao = new QuestionDao();

    @Override
    public String execute(Map<String, String> params, Map<String, Object> model) throws SQLException {
        Question question = new Question(
                params.get("writer"),
                params.get("title"),
                params.get("contents"),
                0);
        Question savedQuestion = questionDao.insert(question);
        System.out.println("saved question id= " + savedQuestion.getQuestionId());
        return "redirect:/";
    }
}
