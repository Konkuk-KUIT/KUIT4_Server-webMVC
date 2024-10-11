package controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RequestMapper {
    private HttpServletRequest req;
    private HttpServletResponse resp;

    private static final Map<String, Controller> controllerMap = new HashMap<>();
    static {
        controllerMap.put("/user/signup", new CreateUserController());
        controllerMap.put("/", new HomeController());
        controllerMap.put("/user/userList", new ListUserController());
        controllerMap.put("/user/login", new LoginController());
        controllerMap.put("/user/logout", new LogoutController());
        controllerMap.put("/user/update", new UpdateUserController());
        controllerMap.put("/user/updateForm", new UpdateUserFormController());
    }

    public RequestMapper(HttpServletRequest req, HttpServletResponse resp) {
        this.req = req;
        this.resp = resp;
    }

    public String proceed() throws ServletException, IOException {
        Controller controller = controllerMap.get(req.getServletPath());
        return controller.execute(req, resp);
    }
}
