package jwp.controller.user;

import core.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutController implements Controller {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        HttpSession session = req.getSession();
        // 현재 세션에서 삭제
        session.removeAttribute("user"); // <-> setAttribute
        return "redirect:/";
    }
}
