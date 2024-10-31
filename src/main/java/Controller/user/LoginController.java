package Controller.user;

import Controller.Controller;
import core.db.MemoryUserRepository;
import jwp.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginController implements Controller {

    @Override
    public String handleRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 로그인 처리 로직을 수행
        String inputUserId = req.getParameter("userId");
        String inputPassword = req.getParameter("password");

        User user = MemoryUserRepository.getInstance().findUserById(inputUserId);
        if (user != null && inputUserId.equals(user.getUserId()) && inputPassword.equals(user.getPassword())) {
            //세션 정보 저장
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
            return "redirect:/"; // 로그인 성공 후 리다이렉트
        } else {
            return "redirect:/user/login_failed.jsp"; // 로그인 실패 후 리다이렉트
        }
    }
}