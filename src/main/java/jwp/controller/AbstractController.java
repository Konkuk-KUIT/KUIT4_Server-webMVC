package jwp.controller;

import core.mvc.Controller;
import core.mvc.view.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public abstract class AbstractController implements Controller {

    @Override
    public ModelAndView execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        return null;
    }

    public abstract ModelAndView jspView(String viewName);
    public abstract ModelAndView jsonView();
}
