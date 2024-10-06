package core;

import core.controller.Controller;
import enums.StatusCode;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/")
public class DispatcherServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getRequestURI();
        RequestMapper requestMapper = new RequestMapper();
        Controller controller = requestMapper.getController(url);

        if (controller != null) {
            String view = controller.handleRequest(request, response);
            if (view.startsWith("redirect:")) {
                response.sendRedirect(view.substring("redirect:".length())); // "redirect:" 제거
            } else {
                request.getRequestDispatcher(view).forward(request, response); // forward
            }
        } else {
            response.sendError(StatusCode.NOT_FOUND.getCode()); // 404 Not Found
        }
    }
}
