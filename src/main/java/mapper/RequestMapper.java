package mapper;

import controller.*;
import servlet.Controller;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class RequestMapper {
    private final Map<String, Controller> controllers = new HashMap<>();

    public RequestMapper() {
        controllers.put("/user/signup", new CreateUserController());
        controllers.put("/", new HomeController());
        controllers.put("/user/userList", new ListUserController());
        controllers.put("/user/login", new LoginController());
        controllers.put("/user/logout", new LogoutController());
        controllers.put("/user/update", new UpdateUserController());
        controllers.put("/user/updateForm", new UpdateUserFormController());
    }

    public Controller getController(String requestUri) {
        return controllers.get(requestUri);
    }

    public String map(HttpServletRequest req) {
        Controller controller = controllers.get(req.getRequestURI());
        if (controller != null) {
            return controller.execute(req);
        }
        return null;
    }
}
