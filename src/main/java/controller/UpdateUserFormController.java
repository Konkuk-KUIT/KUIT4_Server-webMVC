package controller;

import Response.Constants.ResponseJSPFile;
import Response.Constants.ResponseType;
import Response.ResponseStringCreator;
import core.db.MemoryUserRepository;
import jwp.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



public class UpdateUserFormController implements Controller {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String pathInfo = req.getPathInfo();
        User foundUser = getUserbyPathInfo(req, pathInfo);
        req.setAttribute("user", foundUser);


        return ResponseStringCreator.create(ResponseType.FORWARD, ResponseJSPFile.USER_UPDATEFORM);
    }

    private static User getUserbyPathInfo(HttpServletRequest req, String pathInfo) {
        if(pathInfo != null && pathInfo.length() > 1){
            String userId = pathInfo.substring(1);

            return MemoryUserRepository.getInstance().findUserById(userId);
        } else {
            return null;
        }
    }
}
