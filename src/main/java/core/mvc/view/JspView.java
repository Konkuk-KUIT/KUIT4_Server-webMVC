package core.mvc.view;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class JspView implements View {

    private final String viewName;
    private static final String REDIRECT_PREFIX = "redirect:";
    public JspView(String viewName) {
        this.viewName = viewName;
    }

    @Override
    public void render(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // viewName과 일치하는 JSP 파일을 사용자에게 보여주는 역할
        // request.setAttribute("model", model);
        for (Map.Entry<String, Object> entry : model.entrySet()) {
            request.setAttribute(entry.getKey(), entry.getValue());
        }

        if (viewName.startsWith(REDIRECT_PREFIX)) {
            // Redirecting
            response.sendRedirect(viewName.substring(REDIRECT_PREFIX.length()));
            return;
        }
        // Forwarding
        RequestDispatcher rd = request.getRequestDispatcher(viewName);
        rd.forward(request, response);
    }
}
