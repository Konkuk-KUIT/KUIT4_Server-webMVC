package core.controller;

import core.db.MemoryUserRepository;
import core.db.Repository;
import enums.URLPath;
import enums.UserAttribute;
import jwp.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class UpdateUserController implements Controller {
    private Repository userRepository = MemoryUserRepository.getInstance();
    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        String userId = request.getParameter(UserAttribute.USER_ID.getKey());
        String password = request.getParameter(UserAttribute.PASSWORD.getKey());
        User user = new User(request.getParameter(UserAttribute.USER_ID.getKey()),
                request.getParameter(UserAttribute.PASSWORD.getKey()),
                request.getParameter(UserAttribute.NAME.getKey()),
                request.getParameter(UserAttribute.EMAIL.getKey()));
        userRepository.addUser(user);
        return "redirect:" + URLPath.LIST.getPath();
    }
}
