package controller;

import MVC.Controller;
import core.db.MemoryUserRepository;
import jwp.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginController implements Controller {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String userId = request.getParameter("userId");
        String password = request.getParameter("password");

        User user = MemoryUserRepository.getInstance().findUserById(userId);

        if (user != null && user.getPassword().equals(password)) {
            // 로그인 성공 -> session에 정보 저장
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            return "redirect:/";
        }
        else {
            return "redirect:/user/login_failed.jsp";
        }
    }
}
