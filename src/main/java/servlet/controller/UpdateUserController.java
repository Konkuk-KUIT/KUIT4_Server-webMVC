package servlet.controller;

import core.db.MemoryUserRepository;
import jwp.model.User;

import javax.servlet.http.HttpServletRequest;

public class UpdateUserController implements Controller {

    private final  MemoryUserRepository memoryUserRepository;
    public UpdateUserController(MemoryUserRepository memoryUserRepository) {
        this.memoryUserRepository = memoryUserRepository;
    }

    @Override
    public String execute(HttpServletRequest req) {

        User user = memoryUserRepository.findUserById(req.getParameter("userId"));
        User updateUser = new User(user.getUserId(),req.getParameter("password"),
                req.getParameter("name"),req.getParameter("email"));
        user.update(updateUser);
        memoryUserRepository.changeUserInfo(user);
        System.out.println("user info update 완료");
        return "redirect:/user/userList";
    }

}