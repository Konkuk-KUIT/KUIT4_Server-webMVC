package jwp.mvc;

import jwp.controller.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class RequestMapper {
    private static final Map<String, Controller> requestMap = new HashMap<>();
    private final HttpServletRequest req;
    private final HttpServletResponse resp;
    private final Controller controller;

    public RequestMapper(HttpServletRequest req, HttpServletResponse resp) {
        this.req = req;
        this.resp = resp;
        controller = requestMap.get(req.getRequestURI());
        requestMap.put("/user/loginuser", new ForwardController("/user/login.jsp"));
        requestMap.put("/user/createuser", new ForwardController("/user/form.jsp"));

        requestMap.put("/user/signup", new CreateUserController());
        requestMap.put("/", new HomeController());
        requestMap.put("/user/userList", new ListUserController());
        requestMap.put("/user/login", new LogInController());
        requestMap.put("/user/logout", new LogOutController());
        requestMap.put("/user/update", new UpdateUserController());
        requestMap.put("/user/updateForm", new UpdateUserFormController());

    }

    public Controller getController(HttpServletRequest request) {
        return requestMap.get(request.getRequestURI());
    }
}
