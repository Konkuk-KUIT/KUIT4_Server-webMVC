package controller;

import core.db.MemoryUserRepository;
import jwp.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginController implements Controller {

    @Override
    public String process(HttpServletRequest req, HttpServletResponse resp) {
        User user = MemoryUserRepository.getInstance().findUserById(req.getParameter("userId"));

        if (user != null && user.getPassword().equals(req.getParameter("password"))) {
            //로그인 성공
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
            return "redirect:/";
        } else {
            //로그인 실패
            return "/user/login_failed.jsp";
        }
    }
}
