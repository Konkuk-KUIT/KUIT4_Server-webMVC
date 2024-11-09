import controller.*;
import core.db.MemoryUserRepository;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

import static constants.RequestURL.*;

public class RequestMapper extends HttpServlet {

    private final HttpServletRequest httpServletRequest;
    private final HttpServletResponse httpServletResponse;

    private static final Map<String, Controller> controllers = new HashMap<>();
    private final Controller controller;

    public RequestMapper(HttpServletRequest req, HttpServletResponse resp) {
        this.httpServletRequest = req;
        this.httpServletResponse = resp;
        controller = controllers.get(req.getRequestURI());
    }
    static {
        // 정적 초기화 블럭
        controllers.put(SIGNUP.getUrl(), new CreateUserController(MemoryUserRepository.getInstance()));
        controllers.put(LOGIN.getUrl(), new LoginController(MemoryUserRepository.getInstance()));
        controllers.put(USER_LIST.getUrl(), new ListUserController(MemoryUserRepository.getInstance()));
        controllers.put(HOME.getUrl(), new HomeController());
        controllers.put(UPDATE.getUrl(), new UpdateUserController(MemoryUserRepository.getInstance()));
        controllers.put(UPDATE_FORM.getUrl(), new UpdateUserFormController(MemoryUserRepository.getInstance()));
        controllers.put(LOGOUT.getUrl(), new LogoutController());
        controllers.put("/", new HomeController());
    }

    public String proceed() {
        // 매핑되는 컨트롤러가 있다면 execute 실행
        if(controller != null) {
            return controller.execute(httpServletRequest, httpServletResponse);
        }

        // 매핑되는 컨트롤러가 없다면
        return httpServletRequest.getRequestURI();
    }
}
