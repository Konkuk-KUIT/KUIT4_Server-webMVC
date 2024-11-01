package controller;

import core.db.MemoryUserRepository;
import core.db.UserDAO;
import jwp.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

public class LoginController implements Controller {

    private final UserDAO userDAO = new UserDAO();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("userId");
        String password = request.getParameter("password");

        try {
            User user = userDAO.findByUserId(userId);

            if (user != null && user.getPassword().equals(password)) {
                // 로그인 성공
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                return "redirect:/";
            } else {
                return "/user/login_failed.jsp";
            }
        }
        catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}

