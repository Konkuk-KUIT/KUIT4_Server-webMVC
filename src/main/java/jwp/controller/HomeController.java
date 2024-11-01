package jwp.controller;

import core.mvc.Controller;
import jwp.dao.QuestionDao;
import jwp.model.Question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeController implements Controller {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        QuestionDao questionDao = new QuestionDao();

        List<Question> questions = questionDao.findAll();

        HashMap<Integer, Question> questionMap = new HashMap<>();

        for (Question question : questions) {
            questionMap.put(questionDao.findId(question), question);
        }

        req.setAttribute("questions", questionMap);
        return "/home.jsp";
    }
}
