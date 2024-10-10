package Controller;

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
    }

    public Controller getController (String requestURI) {
        return controllers.get(requestURI);
    }
}
