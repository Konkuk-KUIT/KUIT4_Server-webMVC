package controller.get;

import controller.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static enums.ViewPath.HOME_JSP;

public class HomeController implements Controller {

    @Override
    public String handleRequest(HttpServletRequest req, HttpServletResponse resp) {
        return HOME_JSP.getPath();
    }
}
