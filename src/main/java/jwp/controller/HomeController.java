package jwp.controller;

import core.mvc.AbstractController;
import core.mvc.Controller;
import core.mvc.modelandview.ModelAndView;
import core.mvc.view.JspView;
import core.mvc.view.View;
import jwp.dao.QuestionDao;
import jwp.model.Question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class HomeController extends AbstractController {

    private final QuestionDao questionDao = new QuestionDao();

    @Override
    public ModelAndView execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        List<Question> questions = questionDao.findAll();

        return jspView().addObject("questions", questions);
    }

    @Override
    protected ModelAndView jspView() {
        return new ModelAndView(new JspView("/home.jsp"));
    }

    @Override
    protected ModelAndView jsonView() {
        return null;
    }
}
