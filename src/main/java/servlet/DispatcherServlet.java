package servlet;

import controller.*;

import javax.servlet.RequestDispatcher;
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

    private Map<String, Controller> controllerMap = new HashMap<>();

    public void init() throws ServletException {
        controllerMap.put("/",new HomeController());
        controllerMap.put("/user/signup",new CreateUserController());
        controllerMap.put("/user/login",new LoginController());
        controllerMap.put("/user/userList", new ListUserController());
        controllerMap.put("/user/logout", new LogoutController());
        controllerMap.put("/user/update", new UpdateUserController());
        controllerMap.put("/user/updateForm", new UpdateUserFormController());
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        Controller controller = controllerMap.get(uri);

        String view = controller.execute(req, resp);
        System.out.println("view:"+view);

        if(view.startsWith("redirect:")){
            resp.sendRedirect(view.substring(9)); // "redirect:" 제거
        }else{
            RequestDispatcher rd = req.getRequestDispatcher(view);
            rd.forward(req,resp);
        }
    }
}
