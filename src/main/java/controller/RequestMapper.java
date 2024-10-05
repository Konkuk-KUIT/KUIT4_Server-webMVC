package controller;


import java.util.HashMap;
import java.util.Map;

import static enums.HttpUrl.*;

public class RequestMapper {
    private Map<String, Controller> mappings;

    public RequestMapper() {
        mappings = new HashMap<>();
        mappings.put(HOME.getValue(), new HomeController());    //완료
        mappings.put(LOGOUT.getValue(), new LogoutUserController());   //완료
        mappings.put(UPDATE.getValue(), new UpdateUserController());   //완료
        mappings.put(UPDATE_FORM.getValue(), new UpdateUserFormController());   //완료
        mappings.put(USER_SIGNUP.getValue(), new CreateUserController()); //완료
        mappings.put(LOGIN.getValue(), new LoginUserController()); //완료
        mappings.put(LIST.getValue(), new ListUserController()); //완료
    }

    public Controller getController(String url) {
        return mappings.get(url);
    }
}

