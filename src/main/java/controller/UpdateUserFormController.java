package controller;

import core.db.MemoryUserRepository;
import jwp.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/edit")
public class UpdateUserFormController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("userId");

        User user = new User(userId,
                request.getParameter("password"),
                request.getParameter("name"),
                request.getParameter("email"));

        MemoryUserRepository.getInstance().changeUserInfo(user);
        System.out.println("user 정보 수정 완료");
        response.sendRedirect("/user/userList");
    }
}