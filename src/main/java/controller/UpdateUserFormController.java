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

@WebServlet("/user/updateForm")
public class UpdateUserFormController extends HttpServlet {

    private static final String USER_SESSION_KEY = "user";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = MemoryUserRepository.getInstance().findUserById(req.getParameter("userId"));
        HttpSession session = req.getSession();
        Object value = session.getAttribute(USER_SESSION_KEY);
        User currentUser = (User) value;

        if(currentUser.getUserId().equals(user.getUserId())) {
            req.setAttribute("user", user);
            RequestDispatcher rd = req.getRequestDispatcher("/user/updateForm.jsp");
            rd.forward(req, resp);
            return;
        }

        resp.sendRedirect("/user/userList");
    }
}
