package core.mvc;

import core.mvc.view.ModelAndView;

public interface JspController extends Controller {
    abstract ModelAndView jspView(String viewname);
}
