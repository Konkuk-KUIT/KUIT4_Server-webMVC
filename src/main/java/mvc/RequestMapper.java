package mvc;


import controller.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class RequestMapper {

    private final HttpServletRequest request;
    private final HttpServletResponse response;

    private static final Map<String, Controller> controllers = new HashMap<>();
    private final Controller controller;

    public RequestMapper(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;

        controller = controllers.get(request.getRequestURI());
        initControllers();
    }

    private void initControllers() {
        controllers.put("/user/signup", new CreateUserController());
        controllers.put("/", new HomeController());
        controllers.put("/user/userList", new ListUserController());
        controllers.put("/user/login", new LoginController());
        controllers.put("/user/logout", new LogoutController());
        controllers.put("/user/update", new UpdateUserController());
        controllers.put("/user/updateForm", new UpdateUserFormController());

    }

    public Controller getController(HttpServletRequest request) {
        return controllers.get(request.getRequestURI());
    }

    public String dispatch() {
        if (controller != null) {
            return controller.execute(request, response);
        }

        return request.getRequestURI();
    }

}

