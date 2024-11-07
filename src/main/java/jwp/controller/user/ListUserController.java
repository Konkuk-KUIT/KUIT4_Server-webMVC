package jwp.controller.user;

import core.mvc.Controller;
import jwp.dao.UserDao;
import jwp.util.UserSessionUtils;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.Map;

public class ListUserController implements Controller {

    private final UserDao userDao = new UserDao();
    private HttpSession session;

    @Override
    public void setSession(HttpSession session) {
        this.session = session;
    }

    @Override
    public String execute(Map<String, String> params, Map<String, Object> model) throws SQLException {
        if (UserSessionUtils.isLogined(session)) {
            model.put("users", userDao.findAll());
            return "/user/list";
        }
        return "redirect:/user/loginForm";
    }
}
