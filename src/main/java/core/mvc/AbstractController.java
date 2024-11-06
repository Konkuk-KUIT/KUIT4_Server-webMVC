package core.mvc;

import core.mvc.view.JsonView;
import core.mvc.view.JspView;
import core.mvc.view.ModelAndView;

public abstract class AbstractController implements Controller {

    protected ModelAndView jspView(String view) {
        return new ModelAndView(new JspView(view));
    }

    protected ModelAndView jsonView() {
        return new ModelAndView(new JsonView());
    }

}
