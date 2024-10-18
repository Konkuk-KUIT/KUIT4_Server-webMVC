package controller;


import java.util.HashMap;
import java.util.Map;

import static enums.HttpUrl.*;

public class RequestMapper {
    private Map<String, Controller> controller;

    public RequestMapper() {
        controller = new HashMap<>();
        controller.put(HOME.getValue(), new HomeController());    //완료
        controller.put(LOGOUT.getValue(), new LogoutUserController());   //완료
        controller.put(UPDATE.getValue(), new UpdateUserController());   //완료
        controller.put(UPDATE_FORM.getValue(), new UpdateUserFormController());   //완료
        controller.put(USER_SIGNUP.getValue(), new CreateUserController()); //완료
        controller.put(LOGIN.getValue(), new LoginUserController()); //완료
        controller.put(USER_LIST.getValue(), new ListUserController()); //완료
    }

    public Controller getController(String url) {
        return controller.get(url);
    }
}

