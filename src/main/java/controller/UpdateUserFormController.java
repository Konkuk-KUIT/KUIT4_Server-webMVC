package controller;

import core.db.MemoryUserRepository;
import jwp.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/updateForm")
public class UpdateUserFormController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("userId");

        User user = MemoryUserRepository.getInstance().findUserById(userId);

        // 사용자 정보를 request에 담아 updateForm.jsp로 포워딩
        req.setAttribute("user", user);
        req.getRequestDispatcher("/user/updateForm.jsp").forward(req, resp);
    }
}