package controller;

import core.db.MemoryUserRepository;
import jwp.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateUserController implements Controller {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String userId = req.getParameter("userId");
        User existsUser = MemoryUserRepository.getInstance().findUserById(userId);

        if (existsUser == null) {
            User user = new User(req.getParameter("userId"),
                    req.getParameter("password"),
                    req.getParameter("name"),
                    req.getParameter("email"));

            MemoryUserRepository.getInstance().addUser(user);
            System.out.println("user 회원가입 완료");
            return "redirect:/user/userList";
        }

        System.out.println("user 이미 존재함");
        return "redirect:/";
    }
}

