package controller;


import Constants.ResponseType;
import core.db.MemoryUserRepository;
import jwp.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

public class ListUserController implements Controller {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 로그인이 되어있을 경우
        if(req.getSession().getAttribute("user") != null){
            Collection<User> users = MemoryUserRepository.getInstance().findAll();

            req.setAttribute("users", users);
            return ResponseType.FORWARD.getType() + "/user/list.jsp";
        } else {
            return ResponseType.REDIRECT.getType() + "/";
        }

    }
}
