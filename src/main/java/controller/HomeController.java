package controller;

import MVC.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HomeController implements Controller {
    @Override
    public String handleRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // "/" url 받아서 home.jsp를 forward 하도록 하기

        //RequestDispatcher rd = req.getRequestDispatcher("home.jsp");
        //rd.forward(req, resp);

        return "/home.jsp";
    }
}
