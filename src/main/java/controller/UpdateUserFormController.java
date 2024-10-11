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
import java.util.Objects;

public class UpdateUserFormController implements Controller {

    @Override
    public String process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String method = req.getMethod();
        if (method.equalsIgnoreCase("GET")) {
            return handleGet(req, resp);  // GET 요청 처리
        } else if (method.equalsIgnoreCase("POST")) {
            return handlePost(req, resp);  // POST 요청 처리
        }

        return("redirect:/");
    }

    private String handleGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("userId");
        // 세션에 저장된 정보 가져오기
        HttpSession session = req.getSession();
        Object value = session.getAttribute("user");
        User user = MemoryUserRepository.getInstance().findUserById(userId);
        if (value == null) {
            return("redirect:/");
        }

        User loginedUser = (User) value;
        if (!Objects.equals(user.getUserId(), loginedUser.getUserId())) {
            System.out.println("다른 계정 수정 접근!");
            return("redirect:/user/userList");
        }

        req.setAttribute("user", user);
        return("/user/updateForm.jsp");
    }

    private String handlePost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User(req.getParameter("userId"),
                req.getParameter("password"),
                req.getParameter("name"),
                req.getParameter("email"));
        MemoryUserRepository.getInstance().changeUserInfo(user);
        System.out.println("user 정보 수정 완료");
        return("redirect:/user/userList");
    }
}
