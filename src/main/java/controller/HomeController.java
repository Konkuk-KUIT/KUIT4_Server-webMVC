package controller;

import controller.constant.URI;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static controller.constant.URI.HOME;

public class HomeController implements Controller {

    @Override
    public String process(HttpServletRequest req, HttpServletResponse resp) {
        return HOME.getJSPPath();
    }
}
