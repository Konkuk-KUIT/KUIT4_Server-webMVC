package MVC;

import controller.*;

import java.util.HashMap;
import java.util.Map;

public class RequestMapper {

    private static Map<String, Controller> mappings = new HashMap<>();

    static {
        mappings.put("/", new HomeController());
        mappings.put("/user/signup", new CreateUserController());
        mappings.put("/user/userList", new ListUserController());
        mappings.put("/user/login", new LoginController());
        mappings.put("/user/logout", new LogoutController());
        mappings.put("/user/update", new UpdateUserController());
        mappings.put("/user/updateForm", new UpdateUserFormController());
    }

    public static Controller getController(String path) {
        return mappings.get(path);
    }
}
