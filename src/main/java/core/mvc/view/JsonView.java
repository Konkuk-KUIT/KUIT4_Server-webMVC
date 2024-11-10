package core.mvc.view;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class JsonView implements View {
    @Override
    public void render(Map<String, Object> model, HttpServletRequest req, HttpServletResponse resp) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        resp.setContentType("application/json;charset=utf-8");
        PrintWriter out = resp.getWriter();
        out.print(objectMapper.writeValueAsString(model));
    }

    /*
    private static Map<String, Object> createModel(HttpServletRequest req) {
        Enumeration<String> names = req.getHeaderNames();
        Map<String, Object> model = new HashMap<>();
        while (names.hasMoreElements()) {
            String name = names.nextElement();
            model.put(name, req.getParameter(name));
        }
        return model;
    }
    이렇게 하면 다 넣게 된다?
     */
}