package controller;

import controller.constant.QueryKey;
import controller.constant.URI;
import core.db.MemoryUserRepository;
import jwp.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static controller.constant.QueryKey.*;
import static controller.constant.URI.ROOT;

public class CreateUserController implements Controller {

    protected MemoryUserRepository memoryUserRepository;

    public CreateUserController(MemoryUserRepository memoryUserRepository) {
        this.memoryUserRepository = memoryUserRepository;
    }

    @Override
    public String process(HttpServletRequest req, HttpServletResponse resp) {
        User user = new User(req.getParameter(USERID.getKey()),
                req.getParameter(PASSWORD.getKey()),
                req.getParameter(NAME.getKey()),
                req.getParameter(EMAIL.getKey()));
        memoryUserRepository.addUser(user);
        System.out.println("user 회원가입 완료");
        return ROOT.getRedirectURI();
    }
}
