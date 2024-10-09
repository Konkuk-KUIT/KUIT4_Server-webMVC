package mapper;

import controller.*;

public class RequestMapper {
    public Controller getController(String path) {
        return switch (path) {
            case "/user/login" -> new LoginController();
            case "/user/userList" -> new ListUserController();
            case "/user/signup" -> new SignupController();
            case "/user/logout" -> new LogoutController();
            case "/user/updateForm" -> new UpdateUserFormController();
            case "/user/update" -> new UpdateUserController();
            default -> new HomeController();
        };
    }
}