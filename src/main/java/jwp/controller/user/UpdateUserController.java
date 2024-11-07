package jwp.controller.user;

import core.jdbc.JdbcTemplate;
import core.mvc.Controller;
import core.mvc.view.JsonView;
import core.mvc.view.JspView;
import core.mvc.view.ModelAndView;
import core.mvc.view.View;
import jwp.controller.AbstractController;
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
        return jspView("redirect:/user/list");
    }

    @Override
    public ModelAndView jspView(String viewName) {
        return new ModelAndView(new JspView(viewName));
    }

    @Override
    public ModelAndView jsonView() {
        return new ModelAndView(new JsonView());
    }
}
