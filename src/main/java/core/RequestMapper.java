package core;

import core.controller.*;
import core.db.MemoryUserRepository;
import core.db.Repository;
import java.util.HashMap;
import java.util.Map;

public class RequestMapper {

    private static final Map<String, Controller> controllers = new HashMap<>();

    private static final ForwardController forwardController = new ForwardController();

    public RequestMapper() {
        // 컨트롤러 등록
        initializeControllers();
    }

    private void initializeControllers() {
        // controllers 맵에 등록
        controllers.put("/user/update", new UpdateUserController());
        controllers.put("/user/signup", new CreateUserController());
        controllers.put("/user/login", new LoginController());
        controllers.put("/user/userList", new ListUserController());
        controllers.put("/", new HomeController());
    }

    public Controller getController(String url) {
        Controller controller = controllers.get(url);
        if (controller != null) {
            return controller;
        } else {
            return forwardController;
        }
    }

}