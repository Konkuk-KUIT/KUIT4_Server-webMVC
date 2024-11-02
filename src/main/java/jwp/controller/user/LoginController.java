package jwp.controller.user;

import core.mvc.Controller;
import core.mvc.JspController;
import core.mvc.view.JspView;
import core.mvc.view.ModelAndView;
import core.mvc.view.View;
import jwp.dao.UserDao;
import jwp.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginController implements JspController {

    private final UserDao userDao = new UserDao();

    @Override
    public ModelAndView execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        HttpSession session = req.getSession();
        String userId = req.getParameter("userId");
        String password = req.getParameter("password");

        User loginUser = new User(userId, password);
        User user = userDao.findByUserId(userId);

        if (user != null && user.isSameUser(loginUser)) {
            return jspView("redirect:/")
                    .addObject("user", user);
        }
        return jspView("redirect:/user/loginFailed");
    }

    @Override
    public ModelAndView jspView(String viewname) {
        final View view = new JspView(viewname);
        final ModelAndView modelAndView = new ModelAndView(view);
        return modelAndView;
    }
}
