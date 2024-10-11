package controller;

import Constants.ResponseType;
import core.db.MemoryUserRepository;
import jwp.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class LoginController implements Controller {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User userFromRequest = MemoryUserRepository.getInstance().findUserById(req.getParameter("userId"));

        if(!isIdExist(userFromRequest)) {
            return ResponseType.REDIRECT.getType() + "/user/login_failed.jsp";
        }

        if(!isPasswordMatchUser(req, userFromRequest)) {
            return ResponseType.REDIRECT.getType() + "/user/login_failed.jsp";
        }

        // 로그인 성공
        HttpSession session = req.getSession();
        session.setAttribute("user", userFromRequest);

        return ResponseType.REDIRECT.getType() + "/";
    }

    private boolean isIdExist(User userFromRequest) {
        return userFromRequest != null;
    }

    private boolean isPasswordMatchUser(HttpServletRequest req, User userFromRequest) {
        return userFromRequest.getPassword().equals(req.getParameter("password"));
    }
}
