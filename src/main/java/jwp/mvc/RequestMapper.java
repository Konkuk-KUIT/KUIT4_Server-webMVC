package jwp.mvc;

import jwp.controller.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

import static jwp.constant.Path.*;

public class RequestMapper {
    private static final Map<String, Controller> requestMap = new HashMap<>();
    private final HttpServletRequest req;
    private final HttpServletResponse resp;
    private final Controller controller;

    public RequestMapper(HttpServletRequest req, HttpServletResponse resp) {
        this.req = req;
        this.resp = resp;
        controller = requestMap.get(req.getRequestURI());
        requestMap.put(USER_LOGIN.getPath(), new ForwardController(LOGIN_FORWARD.getPath()));
        requestMap.put(USER_CREATE.getPath(), new ForwardController(FORM_FORWARD.getPath()));

        requestMap.put(SIGNUP.getPath(), new CreateUserController());
        requestMap.put(ROOT.getPath(), new HomeController());
        requestMap.put(LIST.getPath(), new ListUserController());
        requestMap.put(LOGIN.getPath(), new LogInController());
        requestMap.put(LOGOUT.getPath(), new LogOutController());
        requestMap.put(UPDATE.getPath(), new UpdateUserController());
        requestMap.put(UPDATEFORM.getPath(), new UpdateUserFormController());

    }

    public Controller getController(HttpServletRequest request) {
        return requestMap.get(request.getRequestURI());
    }
}
