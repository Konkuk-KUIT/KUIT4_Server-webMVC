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

@WebServlet("/user/userList")
public class ListUserController extends HttpServlet {

    private static final String USER_SESSION_KEY = "user"; // 세션에 저장된 사용자 정보의 키

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Object value = session.getAttribute(USER_SESSION_KEY);

        // 로그인 되어 있지 않으면 로그인 페이지로 redirect
        if (value == null) {
            resp.sendRedirect("/user/login.jsp");
            return;
        }

        Collection<User> users = MemoryUserRepository.getInstance().findAll();
        req.setAttribute("users", users);
        RequestDispatcher rd = req.getRequestDispatcher("/user/list.jsp");
        rd.forward(req, resp);
    }
}
