package core.mvc;

import core.mvc.view.JsonView;
import core.mvc.view.JspView;
import core.mvc.view.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class AbstractController implements Controller {

    public ModelAndView jspView(String viewName) {
        return new ModelAndView(new JspView(viewName));
    }

    public ModelAndView jsonView() {
        return new ModelAndView(new JsonView());
    }

    @Override
    public ModelAndView execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        return doExecute(req, resp);
    }

    public abstract ModelAndView doExecute(HttpServletRequest req, HttpServletResponse resp) throws Exception;
}