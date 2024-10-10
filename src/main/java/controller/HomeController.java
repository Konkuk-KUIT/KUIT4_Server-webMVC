package controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebServlet("/")
public class HomeController implements Controller {

//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        RequestDispatcher rd = req.getRequestDispatcher("home.jsp");
//        System.out.println("home으로...");
//        rd.forward(req,resp);
//    }

    @Override
    public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        RequestDispatcher rd = req.getRequestDispatcher("home.jsp");
        System.out.println("home으로...");
//        rd.forward(req,resp);
        return("home.jsp");
    }
}
