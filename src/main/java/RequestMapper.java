import controller.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

public class RequestMapper {
    private final HttpServletRequest req;
    private final HttpServletResponse resp;

    private static final Map<String, Controller> controllers = new HashMap<>();
    private final Controller controller;

    public RequestMapper(HttpServletRequest req, HttpServletResponse resp) {
        this.req = req;
        this.resp = resp;
        controller = controllers.get(req.getRequestURI());
    }
    static {
        controllers.put("/user/signup", new CreateUserController());
        controllers.put("/user/login", new LoginController());
        controllers.put("/user/logout", new LogoutController());
        controllers.put("/user/userList", new ListUserController());
        controllers.put("/user/update", new UpdateUserFormController());
        controllers.put("/", new HomeController());
    }

    public String proceed() throws Exception {
        if (controller != null) {
            return controller.process(req, resp);
        }
        return null;
    }
}
