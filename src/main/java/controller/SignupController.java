package controller;

import core.db.MemoryUserRepository;
import core.db.UserDAO;
import jwp.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;


public class SignupController implements Controller {

    private final UserDAO userDAO = new UserDAO();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 폼으로부터 전달된 데이터 수집
        String userId = req.getParameter("userId");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String email = req.getParameter("email");

        try {
            // 새로운 유저 객체 생성 (User 클래스는 도메인 객체로 가정)
            User newUser = new User(userId, password, name, email);
            userDAO.insert(newUser);

            return "redirect:/";
        } catch (SQLException e) {
            throw new ServletException("서블릿 오류", e);
        }
    }
}

