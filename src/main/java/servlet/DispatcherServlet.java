package servlet;

import webserver.RequestMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/")
public class DispatcherServlet extends HttpServlet {
    RequestMapper requsetMapper;

    @Override
    public void init() throws ServletException {
        this.requsetMapper = new RequestMapper();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String viewName = requsetMapper.map(req);

        if (viewName.startsWith("redirect:")) {
            resp.sendRedirect(viewName.substring(9)); // "redirect:" 제거 후 리다이렉트
            return;
        }
        req.getRequestDispatcher(viewName).forward(req, resp);

    }

}
