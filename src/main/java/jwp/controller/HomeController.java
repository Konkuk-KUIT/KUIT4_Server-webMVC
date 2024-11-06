package jwp.controller;

import core.mvc.controller.AbstractController;
import core.mvc.view.JspView;
import core.mvc.view.ModelAndView;
import jwp.dao.QuestionDao;
import jwp.model.Question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class HomeController implements AbstractController {

    private final QuestionDao questionDao = new QuestionDao();

    @Override
    public ModelAndView execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        List<Question> questions = questionDao.findAll();
        ModelAndView mav = new ModelAndView(new JspView("home.jsp"));
        return mav.addObject("questions", questions);
    }
}
