package controller;

import core.db.MemoryUserRepository;
import core.db.UserDAO;
import jwp.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class UpdateUserFormController implements Controller {

    private final UserDAO userDAO = new UserDAO();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userId = request.getParameter("userId");

        try {
            User user = userDAO.findByUserId(userId);

            if (user == null) {
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "사용자를 찾을 수 없습니다.");
                return "/user/signup.jsp";
            }

            request.setAttribute("user", user);
            return "/user/updateForm.jsp";
        }catch (SQLException e) {
            throw new ServletException("서블릿 오류", e);
        }
    }
}