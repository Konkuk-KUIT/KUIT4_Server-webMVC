package core.mvc;

import core.mvc.view.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class AbstractController implements Controller{
    @Override
    public ModelAndView execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        return null;
    }

    protected abstract ModelAndView jspView(String path);

    protected abstract ModelAndView jsonView();

}
