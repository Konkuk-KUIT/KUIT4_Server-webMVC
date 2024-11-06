package jwp.controller.user;

import core.mvc.Controller;
import core.mvc.modelandview.ModelAndView;
import core.mvc.view.JspView;
import core.mvc.view.View;
import jwp.dao.UserDao;
import jwp.util.UserSessionUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ListUserController implements Controller {

    private final UserDao userDao = new UserDao();

    @Override
    public ModelAndView execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        HttpSession session = req.getSession();
        if (UserSessionUtils.isLogined(session)) {
            return new ModelAndView(new JspView("/user/list.jsp")).addObject("users", userDao.findAll());
        }
        return new ModelAndView(new JspView("redirect:/user/loginForm"));
    }
}