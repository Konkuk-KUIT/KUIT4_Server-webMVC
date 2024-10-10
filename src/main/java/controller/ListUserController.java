package controller;

import core.db.MemoryUserRepository;
import jwp.model.User;
import servlet.Controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collection;

public class ListUserController implements Controller {

    private static final String USER_SESSION_KEY = "user";

    @Override
    public String execute(HttpServletRequest req) {
        HttpSession session = req.getSession();
        Object value = session.getAttribute(USER_SESSION_KEY);

        if (value == null) {
            return "redirect:/user/login.jsp";
        }

        Collection<User> users = MemoryUserRepository.getInstance().findAll();
        req.setAttribute("users", users);
        return "/user/list.jsp";
    }
}
