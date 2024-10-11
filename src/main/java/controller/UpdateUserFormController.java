package controller;

import core.db.MemoryUserRepository;
import jwp.model.User;
import servlet.Controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class UpdateUserFormController implements Controller {

    private static final String USER_SESSION_KEY = "user";

    @Override
    public String execute(HttpServletRequest req) {
        User user = MemoryUserRepository.getInstance().findUserById(req.getParameter("userId"));
        HttpSession session = req.getSession();
        Object value = session.getAttribute(USER_SESSION_KEY);
        User currentUser = (User) value;

        if(currentUser.getUserId().equals(user.getUserId())) {
            req.setAttribute("user", user);

            return "/user/updateForm.jsp";
        }

        return "redirect:/user/userList";
    }
}
