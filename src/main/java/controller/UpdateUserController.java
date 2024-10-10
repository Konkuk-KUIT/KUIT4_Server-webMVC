package controller;

import core.db.MemoryUserRepository;
import jwp.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateUserController implements Controller {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        User user = new User(req.getParameter("userId"),
                req.getParameter("passwrod"),
                req.getParameter("name"),
                req.getParameter("email")
        );

        MemoryUserRepository.getInstance().addUser(user);
        System.out.println("user  업데이트 완료");
        return "redirect:/user/userList";
    }
}
