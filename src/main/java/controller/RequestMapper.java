package controller;


import java.util.HashMap;
import java.util.Map;

public class RequestMapper {
    private Map<String, Controller> mappings;

    public RequestMapper() {
        mappings = new HashMap<>();
        mappings.put("/user/logout", new LogoutUserController());
        mappings.put("/user/update", new UpdateUserController());
        mappings.put("/user/updateForm", new UpdateUserFormController());
        mappings.put("/user/signup", new CreateUserController());
        mappings.put("/user/login", new LogoutUserController());
    }

    public Controller getController(String url) {
        return mappings.get(url);
    }
}

