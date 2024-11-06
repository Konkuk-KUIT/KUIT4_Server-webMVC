package jwp.controller.user;

import core.mvc.AbstractController;
import core.mvc.Controller;
import core.mvc.modelandview.ModelAndView;
import core.mvc.view.JspView;
import core.mvc.view.View;
import jwp.dao.UserDao;
import jwp.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateUserController extends AbstractController {

    private final UserDao userDao = new UserDao();

    @Override
    public ModelAndView execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        User modifiedUser = new User(
                req.getParameter("userId"),
                req.getParameter("password"),
                req.getParameter("name"),
                req.getParameter("email"));
        userDao.update(modifiedUser);
        return jspView();
    }

    @Override
    protected ModelAndView jspView() {
        return new ModelAndView(new JspView("redirect:/user/list"));
    }

    @Override
    protected ModelAndView jsonView() {
        return null;
    }
}
