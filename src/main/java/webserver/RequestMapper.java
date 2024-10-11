package webserver;

import controller.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

import static constants.URL.*;

public class RequestMapper extends HttpServlet {

    private final HttpServletRequest httpServletRequest;
    private final HttpServletResponse httpServletResponse;

    private static final Map<String, Controller> controllers = new HashMap<>();
    private final Controller controller;

    static {
        controllers.put(SIGNUP.getUrl(), new CreateUserController());
        controllers.put(LOGIN.getUrl(), new LoginController());
        controllers.put(USER_LIST.getUrl(), new ListUserController());
        controllers.put(HOME_JSP.getUrl(), new HomeController());
        controllers.put(UPDATE.getUrl(), new UpdateUserController());
        controllers.put(UPDATE_FORM.getUrl(), new UpdateUserFormController());
        controllers.put(LOGOUT.getUrl(), new LogoutController());
        controllers.put("/", new HomeController());
    }
    public RequestMapper(HttpServletRequest req, HttpServletResponse resp) {
        this.httpServletRequest = req;
        this.httpServletResponse = resp;
        controller = controllers.get(req.getRequestURI());
    }


    public String proceed() throws Exception {
        // 매핑되는 컨트롤러가 있다면 execute 실행
        if(controller != null) {
            return controller.execute(httpServletRequest, httpServletResponse);
        }

        // 매핑되는 컨트롤러가 없다면
        return httpServletRequest.getRequestURI();
    }
}
