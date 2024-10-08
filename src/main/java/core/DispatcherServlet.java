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
    private static final RequestMapper requestMapper = new RequestMapper();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        process(request, response);
    }

    private static void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getRequestURI();
        Controller controller = requestMapper.getController(url);
        // 정적 자원 요청을 필터링
        if (url.endsWith(".html") || url.endsWith(".css") || url.endsWith(".js")) {
            // 정적 자원 파일을 직접 반환
            request.getRequestDispatcher(url).forward(request, response);
            return;
        }

        if (controller != null) {
            try {
                String view = controller.handleRequest(request, response);
                if (view.startsWith("redirect:")) {
                    response.sendRedirect(view.substring("redirect:".length())); // "redirect:" 제거

                } else {
                    request.getRequestDispatcher(view).forward(request, response); // forward
                }
            } catch (IOException e) {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "서버 내부 오류 발생"); // 500 에러
            }
        } else {
            response.sendError(StatusCode.NOT_FOUND.getCode()); // 404 Not Found
        }
    }

}
