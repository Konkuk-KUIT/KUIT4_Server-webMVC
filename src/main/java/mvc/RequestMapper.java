package mvc;


import controller.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

import static constants.RequestURI.*;

public class RequestMapper {

    private final HttpServletRequest request;
    private final HttpServletResponse response;

    private static final Map<String, Controller> controllers = new HashMap<>();
    private final Controller controller;

    public RequestMapper(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;

        initControllers();
        controller = controllers.get(request.getRequestURI());
    }

    private void initControllers() {
        controllers.put(SIGNUP.getUri(), new CreateUserController());
        controllers.put(ROOT.getUri(), new HomeController());
        controllers.put(USER_LIST.getUri(), new ListUserController());
        controllers.put(LOGIN.getUri(), new LoginController());
        controllers.put(LOGOUT.getUri(), new LogoutController());
        controllers.put(UPDATE.getUri(), new UpdateUserController());
        controllers.put(UPDATE_FORM.getUri(), new UpdateUserFormController());
    }

    public String dispatch() {
        if (controller != null) {
            return controller.execute(request, response);
        }

        return request.getRequestURI();
    }

}

