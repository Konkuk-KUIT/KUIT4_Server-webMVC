package controller;

import core.db.MemoryUserRepository;
import jwp.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/user/login")
public class LoginController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("userId");
        String password = req.getParameter("password");

        User user = MemoryUserRepository.getInstance().findUserById(userId);

        // 로그인 성공, 세션에 사용자 정보 저장
        if (user != null && user.matchPassword(password)) {
            System.out.println("login 성공");

            HttpSession session = req.getSession();
            session.setAttribute("user", user);       // 세션에 사용자 정보 저장
            resp.sendRedirect("/");                 // 로그인 후 메인 페이지로 리다이렉트
            return;
        }

        // 로그인 실패
        req.getRequestDispatcher("/user/login_failed.jsp").forward(req, resp);

    }
}
