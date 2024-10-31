package controller;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/")

public class DispatcherServlet extends HttpServlet {
    //여기서 컨틀ㄹ러 이어주기?
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            processRequest(req, resp); // GET 요청 처리

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp); // POST

    }

    private void processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 컨트롤러 매핑
        RequestMapper requestMapper = new RequestMapper(req, resp);
        String requestUri = req.getRequestURI(); // 요청된 URI

        Controller controller = requestMapper.getController(requestUri);

        if (controller != null) {
            String result = controller.execute(req, resp);
            if (result.startsWith("redirect:")) {
                String redirectUrl = result.substring("redirect:".length());
                resp.sendRedirect(redirectUrl);
                return; // 종료
            }
            req.getRequestDispatcher(result).forward(req, resp);
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Controller does not exist");
        }

    }


}
