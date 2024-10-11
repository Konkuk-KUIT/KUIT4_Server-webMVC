package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogoutController implements Controller {

    @Override
    public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //세션 데이터 삭제
        HttpSession session = req.getSession();
        session.removeAttribute("user");

        System.out.println("로그아웃 완료");
        return("redirect:/");
    }
}
