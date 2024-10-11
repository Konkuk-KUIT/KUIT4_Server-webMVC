package controller;

import core.db.MemoryUserRepository;
import jwp.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateUserController implements Controller {
    // extends HttpServlet

    // ctrl + o : override method 가져오기
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        // post로 user 객체 받아와서 request의 parameter 받기
//        User user = new User(req.getParameter("userId"),
//                req.getParameter("password"),
//                req.getParameter("name"),
//                req.getParameter("email"));
//
//        // 받아온 user를 DB에 저장
//        MemoryUserRepository.getInstance().addUser(user);
//        System.out.println("user 회원가입 완료");
//        resp.sendRedirect("/user/userList");
//    }

    @Override
    public String handleRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User(req.getParameter("userId"),
                req.getParameter("password"),
                req.getParameter("name"),
                req.getParameter("email"));

        MemoryUserRepository.getInstance().addUser(user);
        System.out.println("User 회원가입 완료");

        return "redirect:/user/userList";
    }
}
