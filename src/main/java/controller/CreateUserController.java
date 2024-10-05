package controller;

import core.db.MemoryUserRepository;
import core.db.Repository;
import jwp.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/signup")  // url 매핑
public class CreateUserController extends HttpServlet {

    Repository repository = MemoryUserRepository.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User(req.getParameter("userId"),
                req.getParameter("password"),
                req.getParameter("name"),
                req.getParameter("email"));

        repository.addUser(user);
        System.out.println("user 회원가입 완료");

        resp.sendRedirect("/user/userList");
    }
}
