package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HomeController implements Controller{

    public String atGet(HttpServletRequest request, HttpServletResponse response) {

        return "/home.jsp";
    }

    public String atPost(HttpServletRequest request, HttpServletResponse response) {
        return null;
    }
}
