package controller;

import core.db.MemoryUserRepository;
import jwp.model.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginController implements Controller{

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String userId = req.getParameter("userId");
        String password = req.getParameter("password");

        User user = MemoryUserRepository.getInstance().findUserById(userId);

        if(user.getPassword().equals(password)){
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
            System.out.println("로그인");
            return "redirect:/";

        }else{
            System.out.println("로그인 실패");
            return "redirect:/user/login_failed.jsp";

        }
    }

}
