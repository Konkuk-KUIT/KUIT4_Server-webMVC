package controller;

import core.db.MemoryUserRepository;
import core.db.Repository;
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

        // 로그인 실패 시 sendRedirect를 사용하여 실패 페이지로 이동시킴
        // 새로운 HTTP 요청이 발생하기 때문에 요청/응답 사이클이 새로 시작됨
        if(user == null || !user.getPassword().equals(password)) {
            resp.sendRedirect( "/user/login_failed.jsp");
            System.out.println("user 로그인 실패");
            return;
        }

        // 로그인 성공 시 forward를 사용하여 로그인 성공 후 데이터를 유지하며 페이지를 렌더링 시킴
        // 기존 요청과 응답을 그대로 전달하기 때문에 같은 요청 객체와 데이터를 사용할 수 있음
        System.out.println("user 로그인 성공");
        HttpSession session = req.getSession();
        session.setAttribute("user", user);
        req.getRequestDispatcher("/home.jsp").forward(req, resp);
    }
}
