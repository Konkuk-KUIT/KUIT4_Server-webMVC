package servlet;

import controller.Controller;
import mapper.RequestMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/")
public class DispatcherServlet extends HttpServlet {
    private RequestMapper requestMapper = new RequestMapper();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI();
        Controller controller = requestMapper.findController(url);

        if (controller == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        String viewName = controller.execute(req, resp);

        if (viewName.startsWith("redirect:")) {
            resp.sendRedirect(viewName.substring(9));
        } else {
            req.getRequestDispatcher(viewName + ".jsp").forward(req, resp);
        }
    }
}
