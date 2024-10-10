package controller;

import controller.constant.URI;
import core.db.MemoryUserRepository;
import jwp.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginController implements Controller {

    protected MemoryUserRepository memoryUserRepository;

    public LoginController(MemoryUserRepository memoryUserRepository) {
        this.memoryUserRepository = memoryUserRepository;
    }

    @Override
    public String process(HttpServletRequest req, HttpServletResponse resp) {
        User user = memoryUserRepository.findUserById(req.getParameter("userId"));

        if (user != null && user.getPassword().equals(req.getParameter("password"))) {
            //로그인 성공
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
            return URI.ROOT.getRedirectURI();
        } else {
            //로그인 실패
            return URI.LOGIN_FAILED.getJSPPath();
        }
    }
}
