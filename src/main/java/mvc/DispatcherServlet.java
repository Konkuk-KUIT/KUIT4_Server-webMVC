package mvc;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/")
public class DispatcherServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        RequestMapper requestMapper = new RequestMapper(req, resp);
        String uri = requestMapper.dispatch();

        if (uri == null || uri.equals(req.getRequestURI())) {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Page Not Found");
            return;
        }

        if (uri.startsWith("redirect:")) {
            String redirectUrl = uri.substring("redirect:".length());

            resp.sendRedirect(redirectUrl);
            return;
        }

        req.getRequestDispatcher(uri).forward(req, resp);


    }
}

