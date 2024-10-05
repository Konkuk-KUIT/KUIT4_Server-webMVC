package controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;


@WebServlet ("/")
public class DispatcherServlet extends HttpServlet {
    private RequestMapper requestMapper;
    private HashSet<String> jspPages;

    @Override
    public void init() throws ServletException {
        requestMapper = new RequestMapper();

        jspPages = new HashSet<>();
        //직접 접근이 가능한 jsp파일들인 경우
        jspPages.add("/index.jsp");
        jspPages.add("/user/form.jsp");
        jspPages.add("/user/login.jsp");
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI();

        if (isJspPage(url)) {
            RequestDispatcher rd = req.getRequestDispatcher(url);
            rd.forward(req, resp);
            return;
        }

        Controller controller = requestMapper.getController(url);

        if (controller == null) {
            resp.sendError(404);
            return;
        }

        String ControllerReturnUrl = controller.execute(req, resp);

        if (ControllerReturnUrl.startsWith("redirect:")) {
            resp.sendRedirect(ControllerReturnUrl.substring(9));
        } else {
            RequestDispatcher rd = req.getRequestDispatcher(ControllerReturnUrl);
            rd.forward(req, resp);
        }
    }

    private boolean isJspPage(String url) {
        return jspPages.contains(url) && !url.contains("../");
    }
}
