package core.mvc;

import jwp.controller.*;
import jwp.controller.question.CreateAnswerController;
import jwp.controller.question.CreateQuestionController;
import jwp.controller.question.CreateQuestionFormController;
import jwp.controller.question.ShowQuestionController;
import jwp.controller.question.UpdateQuestionController;
import jwp.controller.question.UpdateQuestionFormController;
import jwp.controller.user.CreateUserController;
import jwp.controller.user.ListUserController;
import jwp.controller.user.LoginController;
import jwp.controller.user.LogoutController;
import jwp.controller.user.UpdateUserController;
import jwp.controller.user.UpdateUserFormController;
import jwp.dao.AnswerDao;
import jwp.dao.QuestionDao;
import jwp.dao.UserDao;

import java.util.HashMap;
import java.util.Map;

public class RequestMapping {

    private static final Map<String, Controller> controllers = new HashMap<>();

    static {
        UserDao userDao = new UserDao();
        QuestionDao questionDao = new QuestionDao();
        AnswerDao answerDao = new AnswerDao();

        controllers.put("/", new HomeController(questionDao, answerDao));
        controllers.put("/user/signup", new CreateUserController(userDao));
        controllers.put("/user/list", new ListUserController(userDao));
        controllers.put("/user/login", new LoginController(userDao));
        controllers.put("/user/logout", new LogoutController());
        controllers.put("/user/update", new UpdateUserController(userDao));
        controllers.put("/user/updateForm", new UpdateUserFormController(userDao));

        controllers.put("/user/form", new ForwardController("/user/form.jsp"));
        controllers.put("/user/loginForm", new ForwardController("/user/login.jsp"));
        controllers.put("/user/loginFailed", new ForwardController("/user/loginFailed.jsp"));

        controllers.put("/qna/form", new CreateQuestionFormController());
        controllers.put("/qna/create", new CreateQuestionController(questionDao));
        controllers.put("/qna/show", new ShowQuestionController(questionDao, answerDao));
        controllers.put("/qna/updateForm", new UpdateQuestionFormController(questionDao));
        controllers.put("/qna/update", new UpdateQuestionController());

        controllers.put("/api/qna/addAnswer", new CreateAnswerController(questionDao, answerDao));
    }

    public Controller getController(String url) {
        return controllers.get(url);
    }
}
