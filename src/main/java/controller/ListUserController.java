package controller;

import core.db.MemoryUserRepository;
import jwp.model.User;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collection;

public class ListUserController implements Controller {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();

        if(session.getAttribute("user") != null) {
            Collection<User> users = MemoryUserRepository.getInstance().findAll();
            req.setAttribute("users", users);

            return "/user/list.jsp";
        } else {
            return "redirect:/user/login.jsp";
        }
    }
}