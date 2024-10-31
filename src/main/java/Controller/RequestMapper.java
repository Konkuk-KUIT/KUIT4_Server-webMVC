package Controller;

import Controller.qna.CreateAnswerController;
import Controller.qna.CreateQuestionController;
import Controller.qna.CreateQuestionFormController;
import Controller.qna.QnaShowController;
import Controller.user.*;

import java.util.HashMap;
import java.util.Map;

public class RequestMapper {

    // URL과 Controller 매핑을 위한 Map
    private final Map<String, Controller> controllers = new HashMap<>();

    public RequestMapper() {
        controllers.put("/", new HomeController());
        controllers.put("/user/signup", new CreateUserController());
        controllers.put("/user/userList", new ListUserController());
        controllers.put("/user/login", new LoginController());
        controllers.put("/user/logout", new LogOutController());
        controllers.put("/user/update", new UpdateUserController());
        controllers.put("/user/updateForm", new UpdateUserFormController());

        controllers.put("/qna/form", new CreateQuestionFormController());
        controllers.put("/qna/create", new CreateQuestionController());
        controllers.put("/qna/show", new QnaShowController());
        controllers.put("/qna/show/answers", new CreateAnswerController());
    }

    public Controller getController (String requestURI) {
        return controllers.get(requestURI);
    }
}
