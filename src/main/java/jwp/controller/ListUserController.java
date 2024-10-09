package jwp.controller;

import core.db.MemoryUserRepository;
import jwp.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Collection;

import static jwp.constant.Path.LIST_FORWARD;
import static jwp.constant.Path.LOGIN_FORWARD;

public class ListUserController implements Controller {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        HttpSession session = req.getSession();
        Object value = session.getAttribute("user");
        if (value != null) {
            Collection<User> users = MemoryUserRepository.getInstance().findAll();
            req.setAttribute("users", users);
            return LIST_FORWARD.getPath();
        }
        return LOGIN_FORWARD.getRedirectPath();
    }
}
