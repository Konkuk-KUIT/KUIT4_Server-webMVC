package controller;

import core.db.MemoryUserRepository;
import jwp.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UpdateUserFormController implements Controller {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        Object value = session.getAttribute("user");
        User loggedUser = (User) value;

        String userId = req.getParameter("userId");
        User user = MemoryUserRepository.getInstance().findUserById(userId);

        if(loggedUser.equals(user)) {
            req.setAttribute("users", user);
            return "/user/updateForm.jsp";
        }
        return "redirect:/user/userList";
    }
}
