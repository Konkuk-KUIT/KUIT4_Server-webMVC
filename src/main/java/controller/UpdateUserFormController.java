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

// todo 사용자 정보가 일치하지 않는 경우 ??

@WebServlet("/user/updateForm")
public class UpdateUserFormController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // session 정보 가져오기
        HttpSession session = request.getSession();
        User loggedInUser = (User) session.getAttribute("user");

        // 로그인 상태가 아닌 경우 login 하도록 redirect
        if (loggedInUser == null) {
            response.sendRedirect("/user/login.jsp");
            return;
        }

        String userId = request.getParameter("userId");

        // ID로 DB에서 user 가져오기
        User user = MemoryUserRepository.getInstance().findUserById(userId);

        // 사용자 정보가 일치하는 경우만
        if (user != null && loggedInUser.getUserId().equals(user.getUserId())) {
            request.setAttribute("user", user);

            // updateForm.jsp로 forward 하기 -> 사용자 ID 정보 미리 넣어놓도록
            RequestDispatcher rd = request.getRequestDispatcher("/user/updateForm.jsp");
            rd.forward(request, response);
        }else {
            response.sendRedirect("/user/userList");
        }
    }
}
