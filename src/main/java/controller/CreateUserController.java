package controller;

import core.db.MemoryUserRepository;
import jwp.model.User;
import org.springframework.lang.Nullable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateUserController implements Controller {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("userId");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String email = req.getParameter("email");

        if (userId == null || password == null || name == null || email == null ||
                userId.isEmpty() || password.isEmpty() || name.isEmpty() || email.isEmpty()) {
            //forward
            return "/user/form.jsp";
        }

        User user = new User(userId, password, name, email);

        MemoryUserRepository.getInstance().addUser(user);
        System.out.println("User created");
        //redirect
        return "redirect:/";
    }
}
