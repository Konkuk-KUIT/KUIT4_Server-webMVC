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

@WebServlet("/user/updateForm")
public class UpdateUserFormController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //로그인 상태에만 해당 url에 접근할 수 있음
        String userId = req.getParameter("userId");
        User accessUser = MemoryUserRepository.getInstance().findUserById(userId);

        HttpSession session = req.getSession();
        User loginedUser = (User) session.getAttribute("user");            //로그인 정보 세션

        if(accessUser.isSameUser(loginedUser)){
            req.setAttribute("user", accessUser);
            req.getRequestDispatcher("/user/updateForm.jsp").forward(req, resp);

            return;
        }

        //다른 User의 정보 수정에 접근 한 경우
        resp.sendRedirect("/user/userList");
    }
}