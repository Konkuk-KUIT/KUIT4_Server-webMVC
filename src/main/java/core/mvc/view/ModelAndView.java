package core.mvc.view;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class ModelAndView {
    View view;
    Map<String,Object> model=new HashMap<>();

    public ModelAndView(View view) {
        this.view = view;
    }

    //model data 추가 함수

    public ModelAndView addObject(String attributeName,Object attributeValue){
        model.put(attributeName,attributeValue);
        return this;
    }

    public void render(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        view.render(model,req,resp);
    }
}
