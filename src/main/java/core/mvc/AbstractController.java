package core.mvc;

import core.mvc.modelandview.ModelAndView;
import core.mvc.view.JspView;

public abstract class AbstractController implements Controller {
    protected abstract ModelAndView jspView();
    protected ModelAndView jspView(String viewName) {
        return new ModelAndView(new JspView(viewName));
    }

    protected abstract ModelAndView jsonView();
}
