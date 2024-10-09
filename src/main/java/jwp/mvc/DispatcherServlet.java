package jwp.mvc;

import jwp.controller.Controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/")
public class DispatcherServlet extends HttpServlet {
    private final String REDIRECT = "redirect:";

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestMapper requestMapper = new RequestMapper(req, resp);
        Controller controller = requestMapper.getController(req);
        if (controller == null) {
            req.getRequestDispatcher(req.getRequestURI()).forward(req, resp);
        }
        send(req, resp, controller);


    }

    private void send(HttpServletRequest req, HttpServletResponse resp, Controller controller) {
        String viewName;
        try {
            viewName = controller.execute(req, resp);
            if (viewName.startsWith(REDIRECT)) {
                resp.sendRedirect(viewName.substring(REDIRECT.length()));
                return;
            }
            req.getRequestDispatcher(viewName).forward(req, resp);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
