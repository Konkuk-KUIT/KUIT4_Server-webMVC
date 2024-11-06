package jwp.controller.user;

import core.mvc.AbstractController;
import core.mvc.view.ModelAndView;
import jwp.dao.UserDao;
import jwp.model.User;

import java.util.Map;

public class CreateUserController extends AbstractController {

    private final UserDao userDao = new UserDao();

    @Override
    public ModelAndView execute(Map<String, String> params) throws Exception {
        User user = new User(params.get("userId"),
                params.get("password"),
                params.get("name"),
                params.get("email"));
        userDao.insert(user);
        System.out.println("user 회원가입 완료");
        return jspView("redirect:/user/list");
    }
}
