package controller;

import core.db.MemoryUserRepository;
import jwp.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static constants.QueryKey.*;
import static constants.RequestURI.USER_LIST;

public class UpdateUserController implements Controller {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        User user = new User(req.getParameter(USER_ID.getQueryKey()),
                req.getParameter(PASSWORD.getQueryKey()),
                req.getParameter(NAME.getQueryKey()),
                req.getParameter(EMAIL.getQueryKey()));

        MemoryUserRepository.getInstance().addUser(user);
        System.out.println("user  업데이트 완료");
        return USER_LIST.getRedirectUri();
    }
}
