package jwp.controller;

import core.db.MemoryUserRepository;
import core.mvc.Controller;
import jwp.dao.UserDao;
import jwp.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UpdateUserFormController implements Controller {

    private final UserDao userDao = new UserDao();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String userId = req.getParameter("userId");         // 수정되는 user
        User user = userDao.findByUserId(userId);

        HttpSession session = req.getSession();                    // 수정하는 user
        User sessionUser = (User) session.getAttribute("user");

        if (user != null && sessionUser != null && user.equals(sessionUser)) {
            return "/user/updateForm.jsp";
        }
        return "redirect:/";
    }
}