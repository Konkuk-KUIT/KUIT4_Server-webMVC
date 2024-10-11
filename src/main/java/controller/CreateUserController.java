package controller;

import core.db.MemoryUserRepository;
import jwp.model.User;
import servlet.Controller;
import javax.servlet.http.HttpServletRequest;

public class CreateUserController implements Controller {

    @Override
    public String execute(HttpServletRequest req) {
        if(req.getMethod().equals("GET")) {
            return "/user/form.jsp";
        }
        User user = new User(req.getParameter("userId"),
                req.getParameter("password"),
                req.getParameter("name"),
                req.getParameter("email"));
        MemoryUserRepository.getInstance().addUser(user);
        System.out.println("user 회원가입 완료");
        return "redirect:/";
    }
}
