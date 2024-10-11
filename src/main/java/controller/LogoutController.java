package controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class LogoutController implements Controller{

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        System.out.println("로그아웃");
        HttpSession session = req.getSession();
        session.removeAttribute("user");

        System.out.println("로그아웃");
        return "redirect:/";
    }

}


