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

        User user = MemoryUserRepository.getInstance().findUserById(req.getParameter("userId"));
        //해당하는 아이디없으면 || 비밀번호 틀리면 로그인실패
        if(user == null || !(user.getPassword().equals(req.getParameter("password")))) {
            resp.sendRedirect("/user/login_failed.jsp");
            System.out.println("로그인 실패");
            return;
        }
        //로그인 성공
        //세션 정보 저장
        System.out.println("로그인 성공");
        HttpSession session = req.getSession();
        session.setAttribute("user", user);
        resp.sendRedirect("/");
    }
}
