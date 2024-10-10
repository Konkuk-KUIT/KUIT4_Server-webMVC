import controller.*;
import controller.constant.URI;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static controller.constant.URI.*;

@WebServlet("/")
public class DispatcherServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestURI = req.getRequestURI();
        RequestMapper requestMapper = new RequestMapper();
        Controller controller = requestMapper.getController(requestURI);

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
