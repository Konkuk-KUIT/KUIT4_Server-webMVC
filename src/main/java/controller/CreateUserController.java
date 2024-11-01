package controller;

import jwp.dao.MemoryUserRepository;
import jwp.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateUserController   implements Controller{

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user= new User(req.getParameter("userId"),
                req.getParameter("password"),
                req.getParameter("name"),
                req.getParameter("email"));
        MemoryUserRepository.getInstance().addUser(user);

        return "redirect:/user/userList";
    }

}
