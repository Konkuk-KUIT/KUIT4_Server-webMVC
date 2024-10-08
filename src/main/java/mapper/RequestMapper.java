package mapper;

import controller.Controller;
import controller.LoginController;

import java.util.HashMap;
import java.util.Map;

public class RequestMapper {

    private Map<String, Controller> mapping = new HashMap<>();

    public RequestMapper() {
        mapping.put("/user/login", new LoginController());
        // 다른 컨트롤러들도 여기에 추가
    }

    public Controller findController(String url) {
        return mapping.get(url);
    }


}
