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

@WebServlet ("/user/login")
public class LoginUserController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MemoryUserRepository memoryUserRepository = MemoryUserRepository.getInstance();
        User user = memoryUserRepository.findUserById(req.getParameter("userId"));

        if (user != null && user.getPassword().equals(req.getParameter("password"))) {
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
            resp.sendRedirect("/index.jsp");
        } else {
            RequestDispatcher rd = req.getRequestDispatcher("/user/login_failed.jsp");
            rd.forward(req, resp);
        }

        /*if (user != null && user.getPassword().equals(req.getParameter("password"))) {
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
            RequestDispatcher rd = req.getRequestDispatcher("/index.jsp");
            rd.forward(req, resp);
        } else {
            RequestDispatcher rd = req.getRequestDispatcher("/user/login_failed.jsp");
            rd.forward(req, resp);
        }*/
    }
}
