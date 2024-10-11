package controller;

import Response.Constants.ResponseJSPFile;
import Response.Constants.ResponseType;
import Response.ResponseStringCreator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HomeController implements Controller {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        return ResponseStringCreator.create(ResponseType.FORWARD, ResponseJSPFile.HOME);

    }
}
