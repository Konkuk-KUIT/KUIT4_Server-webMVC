package jwp.controller.user;

import core.mvc.AbstractController;
import core.mvc.Controller;
import core.mvc.modelandview.ModelAndView;
import core.mvc.view.JspView;
import core.mvc.view.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutController extends AbstractController {

    @Override
    public ModelAndView execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        HttpSession session = req.getSession();
        session.removeAttribute("user");
        return jspView();
    }

    @Override
    protected ModelAndView jspView() {
        return new ModelAndView(new JspView("redirect:/"));
    }

    @Override
    protected ModelAndView jsonView() {
        return null;
    }
}
