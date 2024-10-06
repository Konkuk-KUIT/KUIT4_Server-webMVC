package core.controller;

import core.db.MemoryUserRepository;
import core.db.Repository;
import enums.URLPath;
import enums.UserAttribute;
import jwp.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateUserController implements Controller {
    private Repository userRepository;
    public CreateUserController(Repository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        User user = new User(request.getParameter(UserAttribute.USER_ID.getKey()),
                request.getParameter(UserAttribute.PASSWORD.getKey()),
                request.getParameter(UserAttribute.NAME.getKey()),
                request.getParameter(UserAttribute.EMAIL.getKey()));
        return "redirect:" + URLPath.INDEX.getPath();
    }
}
