package controller;

import core.db.MemoryUserRepository;
import jwp.model.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;


public class UpdateUserController implements Controller{

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String userId = req.getParameter("userId");
        User user = MemoryUserRepository.getInstance().findUserById(userId);

        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String email = req.getParameter("email");

        User updateUser = new User(user.getUserId(), password, name, email);

        user.update(updateUser);
        MemoryUserRepository.getInstance().changeUserInfo(updateUser);

        Collection<User> users = MemoryUserRepository.getInstance().findAll();
        req.setAttribute("users", users);

        System.out.println("update유저: " + updateUser.toString());

        return "/user/list.jsp";

    }

}
