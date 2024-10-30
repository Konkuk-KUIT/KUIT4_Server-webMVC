package core.mvc;

import jwp.controller.*;

import java.util.HashMap;
import java.util.Map;

public class RequestMapping {

    private static final Map<String, Controller> controllers = new HashMap<>();

    static {
        controllers.put("/", new HomeController());
        controllers.put("/user/signup", new CreateUserController());
        controllers.put("/user/list", new ListUserController());
        controllers.put("/user/login", new LoginController());
        controllers.put("/user/logout", new LogoutController());
        controllers.put("/user/update", new UpdateUserController());
        controllers.put("/user/updateForm", new UpdateUserFormController());

        controllers.put("/user/form", new ForwardController("/user/form.jsp"));
        controllers.put("/user/loginForm", new ForwardController("/user/login.jsp"));
        controllers.put("/user/loginFailed", new ForwardController("/user/loginFailed.jsp"));

//        controllers.put("/qna/form", new ForwardController("/qna/form.jsp"));
        controllers.put("/qna/form", new QuestionFormController());  // 로그인 확인 후 폼 접근
        controllers.put("/qna/show", new ForwardController("/qna/show.jsp"));
        controllers.put("/qna/create", new CreateQuestionController());
    }

    public Controller getController(String url) {
        return controllers.get(url);
    }
}
