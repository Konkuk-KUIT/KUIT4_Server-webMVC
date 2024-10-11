package controller;

import core.db.MemoryUserRepository;
import jwp.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collection;

public class ListUserController implements Controller{

    public String atGet(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        User loggedInUser = (User) session.getAttribute("user");

        if (loggedInUser == null) {
            return "redirect:/";
        }

        Collection<User> users = MemoryUserRepository.getInstance().findAll();
        request.setAttribute("users", users);
        return "/user/list.jsp";
    }

    public String atPost(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }
}
