package controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DispatcherServlet extends HttpServlet {
    private RequestMapper requestMapper = new RequestMapper();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI();
        Controller controller = requestMapper.getController(url);

        if (controller != null) {
            String viewPath = null;
            try {
                viewPath = controller.execute(req, resp);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            if (viewPath.startsWith("redirect:")) {
                resp.sendRedirect(viewPath.substring(9)); // redirect 처리
            } else {
                req.getRequestDispatcher(viewPath).forward(req, resp); // forward 처리
            }
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND); // 404 처리
        }
    }
}
