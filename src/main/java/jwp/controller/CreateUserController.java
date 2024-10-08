package jwp.controller;

import core.db.MemoryUserRepository;
import jwp.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateUserController implements Controller {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String userId = req.getParameter("userId");
        User findUser = MemoryUserRepository.getInstance().findUserById(userId);
        if (findUser == null) {
            User user = new User(req.getParameter("userId"),
                    req.getParameter("password"),
                    req.getParameter("name"),
                    req.getParameter("email"));
            MemoryUserRepository.getInstance().addUser(user);
            System.out.println("User created");
            return "redirect:/user/userList";
        }
        System.out.println("User already exists");
        return "redirect:/";

    }
}
