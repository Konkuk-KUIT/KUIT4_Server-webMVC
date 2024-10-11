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

    @Override

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Object value = session.getAttribute("user");        // 세션에 저장된 정보 가져오기

        //세션에 정보 있음 = 로그인 상태
        if(value != null){
            Collection<User> users = MemoryUserRepository.getInstance().findAll();

            req.setAttribute("users", users);
            RequestDispatcher rd = req.getRequestDispatcher("/user/list.jsp");
            rd.forward(req, resp);

            return;
        }

        //로그인 상태 아님
        resp.sendRedirect("/user/login");       //LoginController 수정
    }
}
