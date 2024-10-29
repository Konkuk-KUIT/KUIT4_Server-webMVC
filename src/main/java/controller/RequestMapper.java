package controller;

import java.util.HashMap;
import java.util.Map;

public class RequestMapper {
    private Map<String, Controller> controllerMap;

    public RequestMapper() {
        controllerMap = new HashMap<>();
        controllerMap.put("/user/signup", new CreateUserController());
        controllerMap.put("/user/list", new ListUserController());
        controllerMap.put("/", new HomeController());
    }
    public Controller getController(String url) {
        return controllerMap.get(url);
    }
}
