package core.mvc;

import core.mvc.view.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ForwardController extends AbstractController {

    private final String forwardUrl;

    public ForwardController(String forwardUrl) {
        this.forwardUrl = forwardUrl;
        if (forwardUrl == null) {
            throw new NullPointerException("forwardUrl is null");
        }
    }

    @Override
    public ModelAndView execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        return jspView(forwardUrl);
    }
}
