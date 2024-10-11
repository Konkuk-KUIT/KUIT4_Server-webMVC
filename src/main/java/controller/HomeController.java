package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HomeController implements Controller {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        return "/home.jsp";
    }

}

