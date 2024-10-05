package controller;

import core.db.MemoryUserRepository;
import jwp.model.User;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateUserController implements Controller {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User(req.getParameter("userId"),
                req.getParameter("password"),
                req.getParameter("name"),
                req.getParameter("email"));

        MemoryUserRepository.getInstance().changeUserInfo(user);

        return "redirect:/user/list";
    }

    /*@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User(req.getParameter("userId"),
                req.getParameter("password"),
                req.getParameter("name"),
                req.getParameter("email"));

        MemoryUserRepository.getInstance().changeUserInfo(user);
        System.out.println("User Edited");
        resp.sendRedirect("/user/list");
    }*/
}
