package controller;

import core.db.MemoryUserRepository;
import jwp.model.User;
import servlet.Controller;
import javax.servlet.http.HttpServletRequest;

public class UpdateUserController implements Controller {

    @Override
    public String execute(HttpServletRequest req) {
        User user = new User(req.getParameter("userId"),
                req.getParameter("password"),
                req.getParameter("name"),
                req.getParameter("email"));
        MemoryUserRepository.getInstance().changeUserInfo(user);
        System.out.println("user 개인정보 수정 완료");
        return "redirect:/user/userList";
    }
}
