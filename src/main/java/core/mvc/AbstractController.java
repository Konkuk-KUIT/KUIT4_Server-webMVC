package core.mvc;

import core.mvc.view.ModelAndView;

public interface AbstractController extends Controller{
    abstract ModelAndView jspView(String url);
    abstract ModelAndView jsonView();
}
