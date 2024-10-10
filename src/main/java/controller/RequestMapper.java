package controller;

import controller.*; // 컨트롤러 클래스들을 임포트

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException; // IOException 예외
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
    }

    public Controller getController(String uri) {
        controller=controllers.get(uri);
        return controller;
    }
}
