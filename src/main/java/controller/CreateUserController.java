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

@WebServlet("/user/signup")
public class CreateUserController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("userId");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String email = req.getParameter("email");

        //회원가입 실패 처리
        if (userId == null || password == null || name == null || email == null ||
                userId.isEmpty() || password.isEmpty() || name.isEmpty() || email.isEmpty()) {
            RequestDispatcher rd = req.getRequestDispatcher("/user/form.jsp");
            rd.forward(req, resp);
        }

        User user = new User(userId, password, name, email);

        MemoryUserRepository.getInstance().addUser(user);
        System.out.println("User created");
        resp.sendRedirect("/");
    }
}
