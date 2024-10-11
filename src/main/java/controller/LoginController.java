package controller;

import core.db.MemoryUserRepository;
import jwp.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collection;

public class LoginController extends HttpServlet implements Controller{

    public String atGet(HttpServletRequest request, HttpServletResponse response) {
        return "/user/login.jsp";
    }

    public String atPost(HttpServletRequest request, HttpServletResponse response) {

        String userId = request.getParameter("userId");
        String password = request.getParameter("password");

        MemoryUserRepository userRepository = MemoryUserRepository.getInstance();
        User user = userRepository.findUserById(userId);

        if (user != null && user.matchPassword(password)) {
            HttpSession session = request.getSession();
            session.setAttribute("user", user);

            return "redirect:" + request.getContextPath() + "/home.jsp";
        } else {
            request.setAttribute("errorMsg", "아이디 또는 비밀번호가 올바르지 않습니다.");
            return "/user/login_failed.jsp";
        }
    }
}