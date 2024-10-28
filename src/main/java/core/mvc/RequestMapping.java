package core.mvc;

import jwp.controller.*;
import jwp.controller.question.*;

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

        controllers.put("/qna/show", new ShowController());
        controllers.put("/qna/form", new QuestionFormController());
        controllers.put("/question/create", new CreateQuestionController());
        controllers.put("/question/delete", new DeleteQuestionController());
        controllers.put("/question/update", new UpdateQuestionController());
        controllers.put("/question/updateForm", new UpdateQuestionFormController());

        controllers.put("/user/form", new ForwardController("/user/form.jsp"));
        controllers.put("/user/loginForm", new ForwardController("/user/login.jsp"));
        controllers.put("/user/loginFailed", new ForwardController("/user/loginFailed.jsp"));

        //controllers.put("/qna/form", new ForwardController("/qna/form.jsp"));
        //controllers.put("/qna/show", new ForwardController("/qna/show.jsp"));
    }

    public Controller getController(String url) {
        return controllers.get(url);
    }
}
