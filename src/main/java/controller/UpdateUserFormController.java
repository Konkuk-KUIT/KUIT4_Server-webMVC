package controller;

import core.db.MemoryUserRepository;
import jwp.model.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



public class UpdateUserFormController implements Controller {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String userId = req.getParameter("userId");
        User user = MemoryUserRepository.getInstance().findUserById(userId);

        req.setAttribute("user", user);

        return "/user/updateForm.jsp";
    }
}

