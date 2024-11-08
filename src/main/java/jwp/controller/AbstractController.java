package jwp.controller;

import core.mvc.Controller;
import core.mvc.view.JsonView;
import core.mvc.view.JspView;
import core.mvc.view.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public abstract class AbstractController implements Controller {

    @Override
    public ModelAndView execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        return null;
    }

    public ModelAndView jspView(String viewName) {
        return new ModelAndView(new JspView(viewName));
    }
    public ModelAndView jsonView() {
        return new ModelAndView(new JsonView());
    }
}