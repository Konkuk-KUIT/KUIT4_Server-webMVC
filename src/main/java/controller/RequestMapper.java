package controller;

import controller.qnaController.CreateQnAController;
import controller.qnaController.QnAFormController;
import controller.qnaController.ShowQnAController;
import controller.qnaController.UpdateQnAController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap; // HashMap 클래스
import java.util.Map; // Map 인터페이스

public class RequestMapper {

    private static final Map<String, Controller> controllers = new HashMap<>(); // URL과 컨트롤러 매핑
    private  Controller controller; // 현재 요청에 해당하는 컨트롤러


    public RequestMapper(HttpServletRequest req,HttpServletResponse resp) {


    }

    // 정적 초기화 블록
    static {
        // URL과 각 컨트롤러 매핑
        controllers.put("/user/signup", new CreateUserController());
        controllers.put("/user/userList", new ListUserController());
        controllers.put("/user/login", new LoginController());
        controllers.put("/user/logout", new LogoutController());
        controllers.put("/user/update", new UpdateUserController());
        controllers.put("/user/updateForm", new UpdateUserFormController());
        controllers.put("/", new HomeController());

        controllers.put("/user/form", new ForwardController("/user/form.jsp"));
        controllers.put("/user/loginForm", new ForwardController("/user/login.jsp"));
        controllers.put("/user/loginFailed", new ForwardController("/user/loginFailed.jsp"));

        controllers.put("/qna/form", new QnAFormController());
        controllers.put("/qna/create", new CreateQnAController());
        controllers.put("/qna/show", new ShowQnAController());
        controllers.put("/qna/update", new UpdateQnAController());

        controllers.put("/qna/updateForm", new UpdateQnAController());


    }

    public Controller getController(String uri) {
        controller=controllers.get(uri);
        return controller;
    }
}
