package jwp.controller.user;

import core.mvc.controller.ControllerV2;
import jwp.dao.UserDao;
import jwp.util.UserSessionUtils;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.Map;

public class ListUserControllerV2 implements ControllerV2 {

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
