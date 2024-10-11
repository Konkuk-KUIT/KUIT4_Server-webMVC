
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
        try {
            String url = requestMapper.proceed();
            if (url != null) {
                if (url.startsWith("redirect:")) {
                    resp.sendRedirect(url.substring(9));
                } else {
                    req.getRequestDispatcher(url).forward(req, resp);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
