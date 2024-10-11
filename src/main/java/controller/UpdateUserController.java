package controller;

import Constants.ResponseType;
import core.db.MemoryUserRepository;
import jwp.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class UpdateUserController implements Controller {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User userGiven = new User(req.getParameter("userId"),
                req.getParameter("password"),
                req.getParameter("name"),
                req.getParameter("email"));
        User editingUser = MemoryUserRepository.getInstance().findUserById(req.getParameter("userId"));

        editingUser.update(userGiven);

        return ResponseType.REDIRECT.getType() + "/user/list";
    }
}
