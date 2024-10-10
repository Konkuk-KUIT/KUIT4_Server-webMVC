import controller.*;
import enums.HttpMethod;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static enums.HttpMethod.*;

@WebServlet("/")
public class DispatcherServlet extends HttpServlet {

    @Override
    public void init() {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp, GET);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp, POST);
    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp, HttpMethod httpMethod) throws ServletException, IOException {
        String path = req.getRequestURI().substring(req.getContextPath().length());
        Controller controller = RequestMapper.getController(path, httpMethod);

        if (controller == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "유효한 경로가 아닙니다.");
            return;
        }

        String handledCommand = controller.handleRequest(req, resp);
        executeCommand(req, resp, handledCommand);
    }

    private void executeCommand(HttpServletRequest req, HttpServletResponse resp, String handledCommand) throws ServletException, IOException {
        if (handledCommand.startsWith("redirect:")) {
            resp.sendRedirect(handledCommand.substring(9));
            return;
        }
        req.getRequestDispatcher(handledCommand).forward(req, resp);
    }
}
