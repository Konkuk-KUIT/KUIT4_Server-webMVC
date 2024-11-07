package jwp.controller;

import core.mvc.controller.ControllerV2;
import jwp.dao.QuestionDao;
import jwp.model.Question;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class HomeControllerV2 implements ControllerV2 {

    private final QuestionDao questionDao = new QuestionDao();

    @Override
    public String execute(Map<String, String> params, Map<String, Object> model) throws SQLException {
        List<Question> questions = questionDao.findAll();
        model.put("questions", questions);
        return "/home";
    }
}
