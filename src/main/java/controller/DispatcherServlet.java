/*
package controller;

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
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //http 요청 들어오면 서비스 메서드 호출, 서비스 메서드로 모든 요청 처리함
        String url = req.getRequestURI(); //현재 요청 uri 가져옴
        Controller controller = requestMapper.getController(url); //현재 url에 해당하는 컨트롤러 찾음

        if (controller != null) {
            String viewPath = null;
            try {
                viewPath = controller.execute(req, resp); //요청 처리, 컨트롤러로부터 뷰 경로 반환
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            if (viewPath.startsWith("redirect:")) {
                resp.sendRedirect(viewPath.substring(9)); // redirect 처리
            } else {
                req.getRequestDispatcher(viewPath).forward(req, resp); // forward 처리
            }
        } else {
            resp.sendError(HttpServletResponse.SC_NOT_FOUND); // controller가 null인 경우: 404 처리
        }
    }
}
*/
