package controller;

import core.db.MemoryUserRepository;
import jwp.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/user/updateForm")
public class UpdateUserFormController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        User updateUser = MemoryUserRepository.getInstance().findUserById(req.getParameter("userId"));
        HttpSession session = req.getSession();

        Object value = session.getAttribute("user");
        User CurrentUser = (User) value;

        if (!CurrentUser.isSameUser(updateUser)) {
            resp.sendRedirect("/user/userList");
            return;
        }
        req.setAttribute("user", updateUser);
        req.getRequestDispatcher("/user/updateForm.jsp").forward(req, resp);
    }
}
