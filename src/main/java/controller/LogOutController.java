package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogOutController implements Controller {

    @Override
    public String process(HttpServletRequest req, HttpServletResponse resp) {
        //세션 데이터 삭제
        HttpSession session = req.getSession();
        session.removeAttribute("user");
        return "redirect:/";
    }
}
