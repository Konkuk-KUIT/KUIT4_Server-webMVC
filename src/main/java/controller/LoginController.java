package controller;

import core.db.MemoryUserRepository;
import jwp.model.User;
import servlet.Controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LoginController implements Controller {

    @Override
    public String execute(HttpServletRequest req) {
        User user = MemoryUserRepository.getInstance().findUserById(req.getParameter("userId"));

        if (user == null || !user.getPassword().equals(req.getParameter("password"))) {
            System.out.println("로그인 실패");
            return "/user/login_failed.jsp";
        }

        System.out.println("로그인 성공");
        HttpSession session = req.getSession();
        session.setAttribute("user", user);
        return "redirect:/";
    }
}
