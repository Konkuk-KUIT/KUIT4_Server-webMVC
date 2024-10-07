package servlet.controller;

import core.db.MemoryUserRepository;
import jwp.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collection;

public class ListUserController implements Controller {

    private final  MemoryUserRepository memoryUserRepository;
    public ListUserController(MemoryUserRepository memoryUserRepository) {
        this.memoryUserRepository = memoryUserRepository;
    }

    @Override
    public String execute(HttpServletRequest req) {

        HttpSession session = req.getSession();
        if(session.getAttribute("user") != null) {
            Collection<User> users = memoryUserRepository.findAll();
            req.setAttribute("users", users);
            return "/user/list.jsp";
        }
        return "redirect:/user/login";
    }

}
