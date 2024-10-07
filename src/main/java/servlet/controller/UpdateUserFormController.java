package servlet.controller;

import core.db.MemoryUserRepository;
import jwp.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class UpdateUserFormController implements Controller {

    private final MemoryUserRepository memoryUserRepository;

    public UpdateUserFormController(MemoryUserRepository memoryUserRepository) {
        this.memoryUserRepository = memoryUserRepository;
    }

    @Override
    public String execute(HttpServletRequest req) {

        User updateUser =  memoryUserRepository.findUserById(req.getParameter("userId"));
        HttpSession session = req.getSession();

        Object value = session.getAttribute("user");
        User CurrentUser = (User) value;

        if (!CurrentUser.isSameUser(updateUser)) {
            return "redirect:/user/userList";
        }

        req.setAttribute("user", updateUser);
        return "/user/updateForm.jsp";

    }

}
