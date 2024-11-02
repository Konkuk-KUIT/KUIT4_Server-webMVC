package jwp.controller.user;

import core.mvc.AbstractController;
import core.mvc.view.ModelAndView;
import jwp.dao.UserDao;
import jwp.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateUserController extends AbstractController {
    private final UserDao userDao = new UserDao();

    @Override
    public ModelAndView doExecute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        User modifiedUser = new User(
                req.getParameter("userId"),
                req.getParameter("password"),
                req.getParameter("name"),
                req.getParameter("email"));
        userDao.update(modifiedUser);

        return jspView("redirect:/user/list");
    }
}
