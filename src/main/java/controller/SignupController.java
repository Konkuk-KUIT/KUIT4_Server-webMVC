package controller;

import core.db.MemoryUserRepository;
import jwp.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


// User 서비스나 DAO에 따라 다르겠지만, 여기에 적절한 유저 생성 로직이 필요함
@WebServlet("/user/signup")
public class SignupController extends HttpServlet {

    private final MemoryUserRepository userRepository = MemoryUserRepository.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 폼으로부터 전달된 데이터 수집
        String userId = req.getParameter("userId");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String email = req.getParameter("email");

        // 간단한 유효성 검사
        if (userId == null || userId.isEmpty() ||
                password == null || password.isEmpty() ||
                name == null || name.isEmpty() ||
                email == null || email.isEmpty()) {
            // 유효하지 않은 경우, 에러 메시지를 담아서 다시 폼으로 이동
            req.setAttribute("errorMessage", "All fields are required!");
            req.getRequestDispatcher("/user/form.jsp").forward(req, resp);
            return;
        }

        // 새로운 유저 객체 생성 (User 클래스는 도메인 객체로 가정)
        User newUser = new User(userId, password, name, email);
        userRepository.addUser(newUser);

        resp.sendRedirect("/");
    }
}

