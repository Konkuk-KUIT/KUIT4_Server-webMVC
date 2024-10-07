package controller;

import core.db.MemoryUserRepository;
import jwp.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collection;

@WebServlet("/user/userList")
public class ListUserController extends HttpServlet {

    private final String USER_SESSION_KEY = "user";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 세션에 저장된 정보 가져와서 Login 정보 체크
        if (isLogin(req.getSession())) {
            //로그인되어 있는 상태라면
            Collection<User> users = MemoryUserRepository.getInstance().findAll();
            req.setAttribute("users", users);

            //jsp에게 위임할 준비(파일 가져옴)
            RequestDispatcher rd = req.getRequestDispatcher("/user/list.jsp");

            //jsp에게 화면 출력에 대한 요청 처리
            rd.forward(req, resp);
        } else {
            //로그인이 되어있지 않은 상태
            resp.sendRedirect("/user/login.jsp");
        }



    }

    private boolean isLogin(HttpSession session) {
        return session.getAttribute(USER_SESSION_KEY) != null;
    }
}
