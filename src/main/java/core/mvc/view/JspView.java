package core.mvc.view;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class JspView implements View {

    private String viewName;
    private static final String REDIRECT_PREFIX = "redirect:";

    public JspView(String viewName) {
        this.viewName = viewName;
    }

    // render 호출 시 해당하는 .jsp로 redirect 혹은 forward
    @Override
    public void render(Map<String,Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        if (viewName.startsWith(REDIRECT_PREFIX)) {
            response.sendRedirect(viewName.substring(REDIRECT_PREFIX.length()));
            return;
        }

        // jsp -> request에 model을 저장
        for (Map.Entry<String, Object> entry : model.entrySet()) {
            request.setAttribute(entry.getKey(), entry.getValue());
        }

        RequestDispatcher rd = request.getRequestDispatcher(viewName);
        rd.forward(request, response);
    }
}
