package core.controller;

import core.db.MemoryUserRepository;
import core.db.Repository;
import enums.URLPath;
import enums.UserAttribute;
import jwp.model.User;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class LoginController implements Controller {
    private Repository userRepository = MemoryUserRepository.getInstance();

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        String userId = request.getParameter(UserAttribute.USER_ID.getKey());
        String password = request.getParameter(UserAttribute.PASSWORD.getKey());
        User user = userRepository.findUserById(userId);
        final boolean authenticated = user != null && password.equals(UserAttribute.PASSWORD.getKey());
        if (authenticated){
            response.addHeader("Set-Cookie", "logined=true");
            return "/";
        }
        return "redirect:" + URLPath.LOGINFAIL.getPath();
    }
}
