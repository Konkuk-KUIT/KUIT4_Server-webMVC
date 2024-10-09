package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class LogoutController implements Controller  {

    @Override
    public String execute (HttpServletRequest req, HttpServletResponse resp) {
        // 세션 데이터 삭제
        HttpSession session = req.getSession(false); // 세션이 존재할 때만 가져옴
        if (session != null) {
            session.invalidate(); // 세션 무효화
            return "redirect:/";
        }
        return "/user/login.jsp";
    }
}
