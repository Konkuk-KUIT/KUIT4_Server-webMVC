package controller;

import core.db.MemoryUserRepository;
import jwp.model.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class CreateUserController implements Controller {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("dkssud");
        User user = new User(req.getParameter("userId"),
            req.getParameter("password"),
            req.getParameter("name"),
            req.getParameter("email"));

        MemoryUserRepository.getInstance().addUser(user);
        System.out.println("user 회원가입 완료");
        System.out.println(user.toString());

        return "redirect:/user/userList";
    }
}


