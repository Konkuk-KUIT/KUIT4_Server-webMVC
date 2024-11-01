package jwp.controller.user;

import core.mvc.Controller;
import jwp.dao.UserDao;
import jwp.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UpdateUserController implements Controller {

    private final UserDao userDao = new UserDao();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String userId = req.getParameter("userId");
        User user = userDao.findByUserId(userId);

        HttpSession session = req.getSession();
        Object value = session.getAttribute("user");

        if (user != null && value != null) {
            if (user.equals(value)) { // 수정 대상 user == 수정 접근 user
                return "/user/updateForm.jsp";
            }
        }
        return "redirect:/";
    }
}
