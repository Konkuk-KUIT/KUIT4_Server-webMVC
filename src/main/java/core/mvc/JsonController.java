package core.mvc;

import core.mvc.view.ModelAndView;

public interface JsonController extends Controller {
    abstract ModelAndView jsonView();
}
