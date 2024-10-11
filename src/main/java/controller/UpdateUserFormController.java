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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String USER_SESSION_KEY = "user";       // loggedInUser 판별
        String accessId = request.getParameter("userId");

        Object value = null;
        if (session != null) {
            value = session.getAttribute(USER_SESSION_KEY);
        }

        if (value != null) {
            User user = (User) value;
            String userId = user.getUserId();

            if (userId.equals(accessId)) {
                request.getRequestDispatcher("/user/updateForm.jsp").forward(request, response);
                return;
            }
            else {
                response.sendRedirect("/user/userList");
                return;
            }
        }
        else {
            response.sendRedirect("/");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String USER_SESSION_KEY = "user";       // loggedInUser 판별

        Object value = null;
        if (session != null) {
            value = session.getAttribute(USER_SESSION_KEY);
        }

        if (value != null) {
            User user = (User) value;
            String userId = user.getUserId();

            User newUser = new User(userId,
                    request.getParameter("password"),
                    request.getParameter("name"),
                    request.getParameter("email"));

            MemoryUserRepository.getInstance().changeUserInfo(newUser);

            session.setAttribute("user", newUser);

            System.out.println("user 정보 수정 완료");
            response.sendRedirect("/user/userList");
        }
    }
}