package controller;

import constants.QueryKey;
import constants.RequestURL;
import core.db.MemoryUserRepository;
import core.db.Repository;
import jwp.model.User;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static constants.QueryKey.*;
import static constants.RequestURL.*;


public class UpdateUserController extends HttpServlet implements Controller{

    Repository repository;

    public UpdateUserController(Repository repository) {
        this.repository = repository;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        User newUser = new User(req.getParameter(ID.getKey()),
                req.getParameter(PASSWORD.getKey()),
                req.getParameter(NAME.getKey()),
                req.getParameter(EMAIL.getKey()));

        User findUser = repository.findUserById(req.getParameter(ID.getKey()));

        findUser.update(newUser);

        // update 완료시 다시 리스트 화면으로 redirect할 수 있도록 url 반환
        return "redirect:" + USER_LIST.getUrl();
    }
}
