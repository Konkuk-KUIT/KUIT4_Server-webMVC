import controller.*;
import core.db.MemoryUserRepository;

import java.util.HashMap;
import java.util.Map;

import static controller.constant.URI.*;

public class RequestMapper {
    private static final Map<String, Controller> controllerMap = new HashMap<>();

    static{
        controllerMap.put(ROOT.getURI(), new HomeController());
        controllerMap.put(SIGNUP.getURI(), new CreateUserController(MemoryUserRepository.getInstance()));
        controllerMap.put(LOGIN.getURI(), new LoginController(MemoryUserRepository.getInstance()));
        controllerMap.put(LOGOUT.getURI(), new LogOutController());
        controllerMap.put(UPDATE_FORM.getURI(), new UpdateUserFormController(MemoryUserRepository.getInstance()));
        controllerMap.put(UPDATE.getURI(), new UpdateUserController(MemoryUserRepository.getInstance()));
        controllerMap.put(USER_LIST.getURI(), new ListUserController(MemoryUserRepository.getInstance()));
    }

    public Controller getController(String uri){
        return controllerMap.get(uri);
    }
}
