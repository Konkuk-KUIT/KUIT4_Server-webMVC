package jwp.controller;

import core.mvc.Controller;
import core.mvc.view.JsonView;
import core.mvc.view.JspView;
import core.mvc.view.ModelAndView;
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
        req.setAttribute("questions", questions);
        return jspView("/home.jsp");
    }

    @Override
    public ModelAndView jspView(String viewName) {
        return new ModelAndView(new JspView(viewName));
    }

    @Override
    public ModelAndView jsonView() {
        return new ModelAndView(new JsonView());
    }
}
