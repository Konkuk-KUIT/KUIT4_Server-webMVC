import controller.Controller;
import controller.get.*;
import controller.post.CreateUserController;
import controller.post.LoginController;
import controller.post.UpdateUserController;
import enums.HttpMethod;

import java.util.HashMap;
import java.util.Map;

import static enums.HttpMethod.GET;
import static enums.HttpMethod.POST;
import static enums.Route.*;

public class RequestMapper {
    private static final Map<String, Controller> controllerGetMap = new HashMap<>();
    private static final Map<String, Controller> controllerPostMap = new HashMap<>();

    static{
        setControllerMaps();
    }

    private static void setControllerMaps() {
        // GET Controller
        controllerGetMap.put(HOME.getRoute(), new HomeController());
        controllerGetMap.put(SIGNUP.getRoute(), new SignUpPageController());
        controllerGetMap.put(LOGIN.getRoute(), new LoginPageController());
        controllerGetMap.put(LOGOUT.getRoute(), new LogoutController());
        controllerGetMap.put(USER_LIST.getRoute(), new ListUserController());
        controllerGetMap.put(UPDATE_FORM.getRoute(), new UpdateUserFormController());

        // POST Controller
        controllerPostMap.put(SIGNUP.getRoute(), new CreateUserController());
        controllerPostMap.put(LOGIN.getRoute(), new LoginController());
        controllerPostMap.put(UPDATE.getRoute(), new UpdateUserController());
    }

    public static Controller getController(String path, HttpMethod httpMethod){
        if(httpMethod.equals(GET)) return controllerGetMap.get(path);
        if(httpMethod.equals(POST)) return controllerPostMap.get(path);
        return null;
    }
}
