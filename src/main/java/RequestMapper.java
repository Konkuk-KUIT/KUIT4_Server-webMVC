import Constants.ResponseURL;
import controller.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/* 매핑 구조를 만들 때, UserFormController의 경우 URL 파라미터에 id값을 받는데요,
    HashMap에서는 *과 같이 아무 값이나 받을 수 있게 하지 못하여
    HttpServletRequest 객체에서 바로 getRequestURI를 하면 매핑이 정상적으로 진행되지 않습니다.
    이에,
    1. HttpServletRequest 객체에서 URL을 복사하고,
    2. 마지막 id값을 뺀 값을 추출해 이 경우만 따로 매핑했습니다.
    RequestMapper에서의 처리가, 나머지 경우 바로 request.getRequestURI로 찾지만 이 한 경우
    (UserFormController에서의 Id값 처리)만 달라서
    이런 경우는 어떻게 처리를 하는 게 효율적인 코드구조를 만들 수 있을지 궁금합니다.
* */
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

    public String proceed() throws ServletException, IOException {
        if(controller != null) {
            return controller.execute(this.request,this.response);
        } else {
            return null;
        }
    };
}
