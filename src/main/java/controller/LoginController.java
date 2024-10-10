package controller;

import core.db.MemoryUserRepository;
import jwp.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class LoginController implements Controller {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User userFromRequest = MemoryUserRepository.getInstance().findUserById(req.getParameter("userId"));
        if(!isIdExist(userFromRequest)) {
            System.err.println("아이디의 계정이 존재하지 않음.");
            resp.sendRedirect("/user/login_failed.jsp");
            return;
        }

        if(!isPasswordMatchUser(req, userFromRequest)) {
            System.err.println("비밀번호가 일치하지 않음.");
            resp.sendRedirect("/user/login_failed.jsp");
            return;
        }

        // 로그인 성공
        HttpSession session = req.getSession();
        session.setAttribute("user", userFromRequest);
        resp.sendRedirect("/");
        System.out.println("로그인 성공");

    }

    private boolean isIdExist(User userFromRequest) {
        return userFromRequest != null;
    }

    private boolean isPasswordMatchUser(HttpServletRequest req, User userFromRequest) {
        return userFromRequest.getPassword().equals(req.getParameter("password"));
    }
}
