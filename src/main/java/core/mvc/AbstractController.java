package core.mvc;

import core.mvc.view.ModelAndView;

public interface AbstractController extends Controller{
    abstract ModelAndView jspView(String viewName);
    abstract ModelAndView jsonView();
}
