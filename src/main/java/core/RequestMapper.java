package core;

import core.controller.*;
import core.db.MemoryUserRepository;
import core.db.Repository;
import java.util.HashMap;
import java.util.Map;

public class RequestMapper {

    private static final Map<String, Controller> controllers = new HashMap<>();

    // DI 주입!!!
    private static final Repository userRepository = (Repository) MemoryUserRepository.getInstance();

    public RequestMapper() {
        // 컨트롤러 등록
        initializeControllers();
    }

    private void initializeControllers() {
        // DI 완료!!!
        LoginController loginController = new LoginController(userRepository);
        CreateUserController createUserController = new CreateUserController(userRepository);

        // controllers 맵에 등록
        controllers.put("/user/signup", createUserController);
        controllers.put("/user/login", loginController);
        controllers.put("/user/userList", new ListUserController());
        controllers.put("/", new HomeController());
    }

    public Controller getController(String url) {
        Controller controller = controllers.get(url);
        if (controller != null) {
            return controller;
        } else {
            return null;
        }
    }

}