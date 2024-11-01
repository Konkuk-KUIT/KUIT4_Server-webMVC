package controller;

import core.db.MemoryUserRepository;
import core.db.UserDAO;
import jwp.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

public class ListUserController implements Controller {

    private static final String USER_SESSION_KEY = "user";
    private final UserDAO userDAO = new UserDAO();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User loggedInUser = (User) session.getAttribute(USER_SESSION_KEY);

        if (loggedInUser == null) {
            // 로그인하지 않은 사용자는 로그인 페이지로 리다이렉트
            return "redirect:/user/login.jsp";
        }

        try {
            // 로그인한 사용자만 목록을 볼 수 있음
            Collection<User> users = userDAO.findAll();
            request.setAttribute("users", users);
            return "/user/list.jsp";
        }catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}