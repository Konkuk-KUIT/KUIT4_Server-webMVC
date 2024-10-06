package webserver;

import controller.*;
import core.db.MemoryUserRepository;
import servlet.controller.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class RequestMapper {

    private static final Map<String, Controller> controllers = new HashMap<>();

    public RequestMapper() {
       controllers.put("/", new HomeController());
       controllers.put("/user/userList", new ListUserController(MemoryUserRepository.getInstance()));
       controllers.put("/user/login", new LoginController(MemoryUserRepository.getInstance()));
       controllers.put("/user/logout", new LogoutController());
       controllers.put("/user/update", new UpdateUserController(MemoryUserRepository.getInstance()));
       controllers.put("/user/updateForm", new UpdateUserFormController(MemoryUserRepository.getInstance()));
       controllers.put("/user/create", new CreateUserController(MemoryUserRepository.getInstance()));
       controllers.put("/user/signup", new CreateUserController(MemoryUserRepository.getInstance()));

    }

    public String map(HttpServletRequest req) {
        Controller controller = controllers.get(req.getRequestURI());
        return controller.execute(req);
    }


}
