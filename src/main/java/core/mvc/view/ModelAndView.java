package core.mvc.view;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class ModelAndView {

    View view;
    Map<String,Object> model = new HashMap<String,Object>();

    public ModelAndView(View view) {
        this.view = view;
    }

    public void addObject(String attributeName, Object attributeValue) {
        model.put(attributeName, attributeValue);
    }

    // todo view Interface 수정 -> model도 넘겨야 함
    public void render(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        view.render(model, req, resp);
    }
}
