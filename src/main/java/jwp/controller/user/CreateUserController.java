package jwp.controller.user;

import core.mvc.Controller;
import jwp.dao.UserDao;
import jwp.model.User;

import java.sql.SQLException;
import java.util.Map;

public class CreateUserController implements Controller {

    private final UserDao userDao = new UserDao();


    @Override
    public String execute(Map<String, String> params, Map<String, Object> model) throws SQLException {
        User user = new User(params.get("userId"),
                params.get("password"),
                params.get("name"),
                params.get("email"));
        userDao.insert(user);
        System.out.println("user 회원가입 완료");
        return "redirect:/user/list";
    }
}
