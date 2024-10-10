package Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogOutController implements Controller {

    @Override
    public String handleRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 세션 데이터 삭제
        HttpSession session = req.getSession();
        session.removeAttribute("user");
        return "redirect:/"; // 로그아웃 후 메인 페이지로 리다이렉트
    }
}