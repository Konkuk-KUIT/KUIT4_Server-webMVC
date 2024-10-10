package controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RequestMapper {
    private final HttpServletRequest request;
    private final HttpServletResponse response;
    private static final Map<String, Controller> controllers = new HashMap<>();
    private Controller controller;

    public RequestMapper(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;

        System.out.println(request.getRequestURI());

        if(isUpdateForm(request)){
            controller = controllers.get(excludeId(request.getRequestURI()));
        } else {
            controller = controllers.get(request.getRequestURI());
        }

    }

    private static String excludeId(String path){
        String[] splitted = path.split("/");
        String result = "";

        for(int i=0;i<splitted.length-1;i++){
            result += splitted[i];
            result += "/";
        }
        return result;
    }

    private static boolean isUpdateForm(HttpServletRequest req) {
        String[] splitted = req.getRequestURI().split("/");

        if(splitted.length >= 3){
            return splitted[2].equals("updateForm");
        } else {
            return false;
        }
    }

    static {
        controllers.put(ResponseURL.HOME.getPath(), new HomeController());
        controllers.put(ResponseURL.USER_LIST.getPath(), new ListUserController());
        controllers.put(ResponseURL.USER_SIGNUP.getPath(), new CreateUserController());
        controllers.put(ResponseURL.USER_LOGIN.getPath(), new LoginController());
        controllers.put(ResponseURL.USER_LOGOUT.getPath(), new LogoutController());
        controllers.put(ResponseURL.USER_UPDATE.getPath(), new UpdateUserController());
        controllers.put(ResponseURL.USER_UPDATE_FORM.getPath(), new UpdateUserFormController());
    }

    public void proceed() throws ServletException, IOException {
        if(controller != null) {
            controller.execute(this.request,this.response);
            return;
        }
    };
}
