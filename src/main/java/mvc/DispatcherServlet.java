package mvc;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static constants.RedirectPrefix.REDIRECT;

@WebServlet("/")
public class DispatcherServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        RequestMapper requestMapper = new RequestMapper(req, resp);
        String uri = requestMapper.dispatch();

        if (uri.startsWith(REDIRECT.getRedirectPrefix())) {
            String redirectUrl = uri.substring(REDIRECT.getRedirectPrefix().length());

            resp.sendRedirect(redirectUrl);
            return;
        }

        if (getServletContext().getResource(uri) != null) {
            req.getRequestDispatcher(uri).forward(req, resp);
            return;
        }

        resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Page Not Found");

    }
}

