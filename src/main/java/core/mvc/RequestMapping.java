package core.mvc;

import core.mvc.controller.ControllerV2;
import jwp.controller.*;
import jwp.controller.qna.*;
import jwp.controller.user.*;

import java.util.HashMap;
import java.util.Map;

public class RequestMapping {

    private static final Map<String, ControllerV2> controllers = new HashMap<>();

    static {
        controllers.put("/", new HomeControllerV2());
        controllers.put("/user/signup", new CreateUserControllerV2());
        controllers.put("/user/list", new ListUserControllerV2());
        controllers.put("/user/login", new LoginControllerV2());
        controllers.put("/user/logout", new LogoutControllerV2());
        controllers.put("/user/update", new UpdateUserControllerV2());
        controllers.put("/user/updateForm", new UpdateUserFormControllerV2());

        controllers.put("/user/form", new ForwardControllerV2("/user/form.jsp"));
        controllers.put("/user/loginForm", new ForwardControllerV2("/user/login.jsp"));
        controllers.put("/user/loginFailed", new ForwardControllerV2("/user/loginFailed.jsp"));

        controllers.put("/qna/form", new CreateQuestionFormControllerV2());
        controllers.put("/qna/updateForm", new UpdateQuestionFormControllerV2());
        controllers.put("/qna/update", new UpdateQuestionControllerV2());
        controllers.put("/qna/create", new CreateQuestionControllerV2());
        controllers.put("/qna/show", new ShowQnaControllerV2());

        controllers.put("/api/qna/addAnswer", new CreateAnswerControllerV2());
    }


    public ControllerV2 getController(String url) {
        return controllers.get(url);
    }
}
