package controller;

import core.db.MemoryUserRepository;
import jwp.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/updateForm")
public class UpdateUserFormController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("userId");

        // ID로 DB에서 user 가져오기
        User user = MemoryUserRepository.getInstance().findUserById(userId);

        request.setAttribute("user", user);

        // updateForm.jsp로 forward 하기 -> 사용자 ID 정보 미리 넣어놓도록
        RequestDispatcher rd = request.getRequestDispatcher("/user/updateForm.jsp");
        rd.forward(request, response);
    }
}
