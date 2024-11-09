package controller;

import constants.RequestURL;
import core.db.MemoryUserRepository;
import core.db.Repository;
import jwp.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Collection;

import static constants.RequestURL.*;


public class ListUserController extends HttpServlet implements Controller {

    Repository repository;

    public ListUserController(Repository repository) {
        this.repository = repository;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        Object value = session.getAttribute("user");

        if(value != null){
            Collection<User> users = repository.findAll();
            req.setAttribute("users", users);

            // 로그인 정보가 존재할 경우 list.jsp 로 forward 할 수 있도록 url 반환
            return USER_LIST_JSP.getUrl();
        }

        // 로그인 정보가 존재하지 않을 경우 로그인 화면으로 redirect 할 수 있도록 url 반환
        return "redirect:" + LOGIN.getUrl();
    }
}
