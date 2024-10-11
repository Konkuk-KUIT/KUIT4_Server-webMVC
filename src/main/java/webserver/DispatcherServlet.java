package webserver;

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
        String url = null;
        try {
            url = requestMapper.proceed();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        // 요청으로 들어온 url에 redirect가 붙어있다면 redirect 수행해줌
        if (url.contains("redirect:")) {
            String redirectUrl = url.substring(url.indexOf("redirect:") + "redirect:".length());

            resp.sendRedirect(redirectUrl);
            return;
        }

        // 파일 존재 여부 확인
        if (getServletContext().getResource(url) != null) {
            req.getRequestDispatcher(url).forward(req, resp);  // 파일이 있으면 forward
            return;
        }

        resp.sendError(HttpServletResponse.SC_NOT_FOUND, "Page Not Found");  // 파일이 없으면 404 에러
    }
}
