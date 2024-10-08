package controller;

import controller.constant.URI;
import core.db.MemoryUserRepository;
import jwp.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static controller.constant.URI.USER_LIST;

public class UpdateUserController implements Controller {

    protected MemoryUserRepository memoryUserRepository;

    public UpdateUserController(MemoryUserRepository memoryUserRepository) {
        this.memoryUserRepository = memoryUserRepository;
    }

    @Override
    public String process(HttpServletRequest req, HttpServletResponse resp) {
        User user = new User(req.getParameter("userId"),
                req.getParameter("password"),
                req.getParameter("name"),
                req.getParameter("email"));

        memoryUserRepository.changeUserInfo(user);
        return USER_LIST.getRedirectURI();
    }
}
