package controller;

import constants.RequestURL;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static constants.RequestURL.*;

public class HomeController extends HttpServlet implements Controller {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        // 홈 화면으로 forward 하도록 url 반환
        return HOME.getUrl();
    }
}
