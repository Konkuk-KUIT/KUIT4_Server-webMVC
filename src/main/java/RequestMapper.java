import controller.*;

import java.util.HashMap;
import java.util.Map;

import static controller.constant.URI.*;

public class RequestMapper {
    private static final Map<String, Controller> controllerMap = new HashMap<>();

    static{
        controllerMap.put(ROOT.getURI(), new HomeController());
        controllerMap.put(SIGNUP.getURI(), new CreateUserController());
        controllerMap.put(LOGIN.getURI(), new LoginController());
        controllerMap.put(LOGOUT.getURI(), new LogOutController());
        controllerMap.put(UPDATE_FORM.getURI(), new UpdateUserFormController());
        controllerMap.put(UPDATE.getURI(), new UpdateUserController());
        controllerMap.put(USER_LIST.getURI(), new ListUserController());
    }

    public Controller getController(String uri){
        return controllerMap.get(uri);
    }
}
