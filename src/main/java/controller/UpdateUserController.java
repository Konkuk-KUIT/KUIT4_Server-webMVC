package controller;

import jwp.dao.MemoryUserRepository;
import jwp.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateUserController implements Controller {


    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String userId = req.getParameter("userId");
        User user = MemoryUserRepository.getInstance().findUserById(userId);
        User updateUser = new User(
                userId,
                req.getParameter("password"),
                req.getParameter("name"),
                req.getParameter("email"));
        user.update(updateUser);
        MemoryUserRepository.getInstance().changeUserInfo(user);
        //resp.sendRedirect("/user/userList");
        return"redirect:/user/userList";
    }
}
