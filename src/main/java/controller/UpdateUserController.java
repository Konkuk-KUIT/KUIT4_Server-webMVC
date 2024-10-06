package controller;

import core.db.MemoryUserRepository;
import jwp.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/update")
public class UpdateUserController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = MemoryUserRepository.getInstance().findUserById(req.getParameter("userId"));
        User updateUser = new User(user.getUserId(),req.getParameter("password"),
                req.getParameter("name"),req.getParameter("email"));
        user.update(updateUser);
        MemoryUserRepository.getInstance().changeUserInfo(user);
        System.out.println("user info update 완료");
        resp.sendRedirect("/user/userList");
    }
}