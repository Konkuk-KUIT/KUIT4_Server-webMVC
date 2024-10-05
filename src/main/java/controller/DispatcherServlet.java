package controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet ("/")
public class DispatcherServlet extends HttpServlet {
    private RequestMapper requestMapper;

    @Override
    public void init() throws ServletException {
        requestMapper = new RequestMapper();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI();
        Controller controller = requestMapper.getController(url);

        if (controller == null) {
            resp.sendError(404);
            return;
        }

        String ControllerReturnUrl = controller.execute(req, resp);

        if (ControllerReturnUrl.startsWith("redirect:")) {
            resp.sendRedirect(url.substring(9));
        } else {
            RequestDispatcher rd = req.getRequestDispatcher(ControllerReturnUrl);
            rd.forward(req, resp);
        }
    }

}
