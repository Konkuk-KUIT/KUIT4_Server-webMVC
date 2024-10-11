package MVC;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/")
public class DispatcherServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // URL Mapping -> Controller 선택
        String path = request.getRequestURI();
        Controller controller = RequestMapper.getController(path);

        if (controller != null) {
            String viewName = controller.handleRequest(request, response);
            // View (forward or redirect)
            if (viewName.startsWith("redirect:")) {
                response.sendRedirect(viewName.substring("redirect:".length()));
            } else {
                RequestDispatcher rd = request.getRequestDispatcher(viewName);
                rd.forward(request, response);
            }
        } else {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Page not found");
        }
    }
}
