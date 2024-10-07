package servlet.controller;

import javax.servlet.http.HttpServletRequest;


public class HomeController implements Controller {

    @Override
    public String execute(HttpServletRequest req) {
        return "home.jsp";
    }

}
