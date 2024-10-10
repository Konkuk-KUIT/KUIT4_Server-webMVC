package controller;

import core.db.MemoryUserRepository;
import jwp.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collection;

public class ListUserController extends HttpServlet implements Controller{
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Collection<User> users = MemoryUserRepository.getInstance().findAll();
        req.setAttribute("users", users);

        HttpSession session = req.getSession();
        Object value = session.getAttribute("user");
        if (value != null) {
            User loginedUser = (User) value;
            req.setAttribute("loginedUser", loginedUser);
            return "/user/list.jsp";
        }
        return "/user/login.jsp";
    }
}
