package Controller.user;

import Controller.Controller;
import core.db.MemoryUserRepository;
import jwp.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateUserFormController implements Controller {

    @Override
    public String handleRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 사용자 정보를 받아서 JSP로 전달
        User userById = MemoryUserRepository.getInstance().findUserById(req.getParameter("userId"));
        req.setAttribute("user", userById);
        return "/user/updateForm";
    }
}
