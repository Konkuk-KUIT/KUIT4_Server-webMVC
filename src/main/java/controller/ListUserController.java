package controller;


import Response.Constants.ResponseJSPFile;
import Response.Constants.ResponseType;
import Response.Constants.ResponseURL;
import Response.ResponseStringCreator;
import core.db.MemoryUserRepository;
import jwp.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

public class ListUserController implements Controller {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 로그인이 되어있을 경우
        if(isLoggined(req)){

            addAllUsersToRequest(req);

            return ResponseStringCreator.create(ResponseType.FORWARD, ResponseJSPFile.USER_LIST);
        } else {
            return ResponseStringCreator.create(ResponseType.REDIRECT, ResponseURL.HOME);
        }

    }

    private void addAllUsersToRequest(HttpServletRequest req) {
        Collection<User> users = MemoryUserRepository.getInstance().findAll();
        req.setAttribute("users", users);
    }

    private boolean isLoggined(HttpServletRequest req) {
        return req.getSession().getAttribute("user") != null;
    }
}
