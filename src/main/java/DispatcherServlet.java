import controller.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet("/")
public class DispatcherServlet extends HttpServlet {

    private static final Map<String, Controller> controllerMap = new HashMap<>();

    static{
        controllerMap.put("/", new HomeController());
        controllerMap.put("/user/signup", new CreateUserController());
        controllerMap.put("/user/login", new LoginController());
        controllerMap.put("/user/logout", new LogOutController());
        controllerMap.put("/user/updateForm", new UpdateUserFormController());
        controllerMap.put("/user/update", new UpdateUserController());
        controllerMap.put("/user/userList", new ListUserController());
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();

        Controller controller = controllerMap.get(requestURI);

        if(controller == null){
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        String resultURI = controller.process(req, resp);
        if (resultURI.startsWith("redirect")) {
            String redirectURI = resultURI.split(":")[1];
            resp.sendRedirect(redirectURI);
        } else {
            req.getRequestDispatcher(resultURI).forward(req, resp);
        }
    }
}
