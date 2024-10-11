import controller.Controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/")
public class DispatcherServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Controller controller = RequestMapper.map(req.getRequestURI());

        if (controller == null) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Controller not found");
        }

        String url = controller.atGet(req, resp);

        if (url != null) {
            if (url.startsWith("redirect:")) {
                url = url.replaceFirst("redirect:", "");
                resp.sendRedirect(url);
            }
            else {
                req.getRequestDispatcher(url).forward(req, resp);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Controller controller = RequestMapper.map(req.getRequestURI());

        if (controller == null) return;

        String url = controller.atPost(req, resp);

        if (url != null) {
            if (url.startsWith("redirect:")) {
                url = url.replaceFirst("redirect:", "");
                resp.sendRedirect(url);
            }
            else {
                req.getRequestDispatcher(url).forward(req, resp);
            }
        }
    }
}
