package jwp.controller;

import core.mvc.AbstractController;
import core.mvc.view.ModelAndView;
import jwp.dao.QuestionDao;
import jwp.model.Question;

import java.util.List;
import java.util.Map;

public class HomeController extends AbstractController {

    private final QuestionDao questionDao = new QuestionDao();

    @Override
    public ModelAndView execute(Map<String, String> params) throws Exception {
        List<Question> questions = questionDao.findAll();
        return jspView("/home.jsp").addObject("questions", questions);
    }
}
