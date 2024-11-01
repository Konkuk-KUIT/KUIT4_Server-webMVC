package core.mvc;

import jwp.controller.*;
import jwp.dao.QuestionDao;
import jwp.dao.UserDao;

import java.util.HashMap;
import java.util.Map;

public class RequestMapping {

    private static final Map<String, Controller> controllers = new HashMap<>();

    static {
        UserDao userDao = new UserDao();
        QuestionDao questionDao = new QuestionDao();
        controllers.put("/", new HomeController(questionDao));
        controllers.put("/user/signup", new CreateUserController(userDao));
        controllers.put("/user/list", new ListUserController(userDao));
        controllers.put("/user/login", new LoginController(userDao));
        controllers.put("/user/logout", new LogoutController());
        controllers.put("/user/update", new UpdateUserController(userDao));
        controllers.put("/user/updateForm", new UpdateUserFormController(userDao));

        controllers.put("/user/form", new ForwardController("/user/form.jsp"));
        controllers.put("/user/loginForm", new ForwardController("/user/login.jsp"));
        controllers.put("/user/loginFailed", new ForwardController("/user/loginFailed.jsp"));

        controllers.put("/qna/form", new QuestionFormController(questionDao));
        controllers.put("/qna/create", new QuestionCreateController(questionDao));
        controllers.put("/qna/show", new QuestionShowController());
        controllers.put("/qna/updateForm", new QuestionUpdateFormController(questionDao));
        controllers.put("/qna/update", new QuestionUpdateController());
    }

    public Controller getController(String url) {
        return controllers.get(url);
    }
}
