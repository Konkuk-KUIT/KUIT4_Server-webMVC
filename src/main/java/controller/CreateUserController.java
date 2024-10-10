package controller;

import core.db.MemoryUserRepository;
import jwp.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static constants.QueryKey.*;
import static constants.RequestURI.*;
import static constants.SessionKey.*;

public class CreateUserController implements Controller {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String userId = req.getParameter(SESSION_KEY.getSessionKey());
        User existsUser = MemoryUserRepository.getInstance().findUserById(userId);

        if (existsUser == null) {
            User user = new User(req.getParameter(USER_ID.getQueryKey()),
                    req.getParameter(PASSWORD.getQueryKey()),
                    req.getParameter(NAME.getQueryKey()),
                    req.getParameter(EMAIL.getQueryKey()));

            MemoryUserRepository.getInstance().addUser(user);
            System.out.println("user 회원가입 완료");
            return USER_LIST.getRedirectUri();
        }

        System.out.println("user 이미 존재함");
        return ROOT.getRedirectUri();
    }
}

