package jwp.controller.user;

import core.mvc.AbstractController;
import core.mvc.view.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Map;

public class LogoutController extends AbstractController {
    private HttpSession session;

    @Override
    public void setSession(HttpSession session) {
        this.session = session;
    }

    @Override
    public ModelAndView execute(Map<String, String> params) throws Exception {
        session.removeAttribute("user");
        return jspView("redirect:/");
    }
}
