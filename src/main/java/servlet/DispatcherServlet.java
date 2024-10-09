package servlet;

import controller.Controller;
import mapper.RequestMapper;

import javax.servlet.RequestDispatcher;
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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // getRequestURI()로 경로를 확인
        String uri = request.getRequestURI();
        String path = uri.substring(uri.indexOf("/"));

        // 로그로 경로 확인
        System.out.println("Requested path: " + path);

        Controller controller = requestMapper.getController(path);

        if (controller == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Controller not found for path: " + path);
            return;
        }

        String view = controller.execute(request, response);

        if (view.startsWith("redirect:")) {
            response.sendRedirect(view.substring("redirect:".length()));
        } else {
            RequestDispatcher rd = request.getRequestDispatcher(view);
            rd.forward(request, response);
        }
    }
}


