package controller;

import Response.Constants.ResponseType;
import Response.Constants.ResponseURL;
import Response.ResponseStringCreator;
import core.db.MemoryUserRepository;
import jwp.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class UpdateUserController implements Controller {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User userGiven = getUserinRequest(req);
        User editingUser = getRequestedUserinRepository(req);

        editingUser.update(userGiven);

        return ResponseStringCreator.create(ResponseType.REDIRECT, ResponseURL.USER_LIST);
    }

    private User getUserinRequest(HttpServletRequest req) {
        return new User(req.getParameter("userId"),
                req.getParameter("password"),
                req.getParameter("name"),
                req.getParameter("email"));
    }

    private User getRequestedUserinRepository(HttpServletRequest req) {
        return MemoryUserRepository.getInstance().findUserById(req.getParameter("userId"));
    }
}
