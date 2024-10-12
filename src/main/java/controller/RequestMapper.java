package controller;

import java.util.HashMap;
import java.util.Map;

public class RequestMapper {
    private Map<String, Controller> controllerMap;

    public RequestMapper() {
        controllerMap = new HashMap<>();
        controllerMap.put("/user/signup", (Controller) new CreateUserController());
        controllerMap.put("/user/list", (Controller) new ListUserController());
    }
    public Controller getController(String url) {
        return controllerMap.get(url);
    }
}
