package controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Controller {
    //Controller는 redirect 시에 “`redirect:/url`” 형식으로 반환한다.
    //Controller는 forward 시에는 “`/url`” 형식으로 반환한다.
    String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;
}
