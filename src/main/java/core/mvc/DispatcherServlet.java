package core.mvc;

import core.mvc.view.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "dispatcher", urlPatterns = "/", loadOnStartup = 1)
public class DispatcherServlet extends HttpServlet {
    private RequestMapping requestMapping;
    private static final String REDIRECT_PREFIX = "redirect:";

    @Override
    public void init() {
        requestMapping = new RequestMapping();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException {
        String url = req.getRequestURI();
        Controller controller = requestMapping.getController(url);
        try {
//            String viewName = controller.execute(req, resp);
//            if (viewName == null) {
//                return;
//            }
//            move(viewName, req, resp);

//            View view = controller.execute(req, resp);
//            if (view == null) {
//                return;
//            }
//            view.render(req, resp);

            ModelAndView mav = controller.execute(req, resp);
            mav.render(req, resp);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new ServletException(e.getMessage());
        }
    }

//    // jspView의 render 메서드로 로직 이동
//    private void move(String viewName, HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
//        if (viewName.startsWith(REDIRECT_PREFIX)) {
//            resp.sendRedirect(viewName.substring(REDIRECT_PREFIX.length()));
//            return;
//        }
//        RequestDispatcher rd = req.getRequestDispatcher(viewName);
//        rd.forward(req, resp);
//    }
}
