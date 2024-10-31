package Controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/")
public class DispatcherServlet extends HttpServlet {

    private RequestMapper requestMapper;

    @Override
    public void init() throws ServletException {
        // RequestMapper 객체 초기화
        requestMapper = new RequestMapper();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String requestURI = req.getRequestURI();
        System.out.println("Requested URI: " + requestURI);

        Controller controller = requestMapper.getController(requestURI);

        if (controller != null) {
            try {
                String view = controller.handleRequest(req, resp);

                // Forward 또는 Redirect 처리
                if (view.startsWith("redirect:")) {
                    resp.sendRedirect(view.substring("redirect:".length()));
                } else {
                    RequestDispatcher rd = req.getRequestDispatcher(view);
                    rd.forward(req, resp);
                }
            } catch (Exception e) {
                resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error processing request: " + e.getMessage());
            }
        } else {
            // 컨트롤러가 없는 경우 404 오류 반환
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "No controller found for: " + requestURI);
        }
    }
}