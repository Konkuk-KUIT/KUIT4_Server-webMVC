package controller.get;

import controller.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static enums.ViewPath.LOGIN_JSP;

public class LoginPageController implements Controller {
    @Override
    public String handleRequest(HttpServletRequest req, HttpServletResponse resp) {
        return LOGIN_JSP.getPath();
    }
}
