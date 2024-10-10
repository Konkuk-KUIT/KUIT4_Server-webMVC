package controller.get;

import controller.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static enums.ViewPath.FORM_JSP;

public class SignUpPageController implements Controller {
    @Override
    public String handleRequest(HttpServletRequest req, HttpServletResponse resp) {
        return FORM_JSP.getPath();
    }
}
