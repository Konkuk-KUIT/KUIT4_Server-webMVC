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
        controller = controllers.get(request.getRequestURI());
    }

    static {
        controllers.put(ResponseURL.HOME.getPath(), new HomeController());
    }

    public void proceed() throws ServletException, IOException {
        if(controller != null) {
            controller.execute(this.request,this.response);
            return;
        }
    };
}
