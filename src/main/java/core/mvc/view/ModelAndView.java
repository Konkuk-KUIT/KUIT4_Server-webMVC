package core.mvc.view;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class ModelAndView {         //사용자에게 보여줄 페이지와 페이지에 전달할 데이터를 모두 담고 있는 클래스
    View view;
    Map<String, Object> model = new HashMap<>();

    public ModelAndView(View view){
        this.view = view;
    }

    public ModelAndView addObject(String attributeName, Object attributeValue){
        model.put(attributeName, attributeValue);
        return this;            //메서드 체이닝을 가능하게 함 (객체 자기 자신이 리턴 됨)
    }

    public void render(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        view.render(model, req, resp);      //model 들어가게 인터페이스 수정 필요
    }
}
