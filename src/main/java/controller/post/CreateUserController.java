package controller.post;

import controller.Controller;
import core.db.MemoryUserRepository;
import jwp.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static enums.Route.LOGIN;
import static enums.UserQueryKey.*;

public class CreateUserController implements Controller {

    @Override
    public String handleRequest(HttpServletRequest req, HttpServletResponse resp) {
        User user = new User(req.getParameter(USERID.getValue()),
                req.getParameter(PASSWORD.getValue()),
                req.getParameter(NAME.getValue()),
                req.getParameter(EMAIL.getValue()));
        MemoryUserRepository.getInstance().addUser(user);
        return LOGIN.getRedirectCommand();
    }
}
