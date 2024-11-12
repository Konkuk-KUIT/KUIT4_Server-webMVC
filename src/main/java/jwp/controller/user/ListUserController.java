package jwp.controller.user;

import core.mvc.AbstractController;
import core.mvc.view.JspView;
import core.mvc.view.ModelAndView;
import jwp.dao.UserDao;
import jwp.util.UserSessionUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ListUserController implements AbstractController {

    private final UserDao userDao = new UserDao();

    @Override
    public ModelAndView execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        HttpSession session = req.getSession();
        if (UserSessionUtils.isLogined(session)) {
            return jspView("/user/list.jsp")
                    .addObject("users", userDao.findAll());
        }
        return jspView("redirect:/user/loginForm");
    }

    @Override
    public ModelAndView jspView(String viewName) {
        return new ModelAndView(new JspView(viewName));
    }

    @Override
    public ModelAndView jsonView() {
        return null;
    }
}