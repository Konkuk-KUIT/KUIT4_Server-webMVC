package controller;


import java.util.HashMap;
import java.util.Map;

public class RequestMapper {
    private Map<String, Controller> mappings;

    public RequestMapper() {
        mappings = new HashMap<>();
        mappings.put("/user/logout", new LogoutUserController());   //완료
        mappings.put("/user/update", new UpdateUserController());   //완료
        mappings.put("/user/updateForm", new UpdateUserFormController());   //완료
        mappings.put("/user/signup", new CreateUserController()); //완료
        mappings.put("/user/login", new LoginUserController()); //완료
        mappings.put("/user/list", new ListUserController()); //완료
    }

    public Controller getController(String url) {
        return mappings.get(url);
    }
}

