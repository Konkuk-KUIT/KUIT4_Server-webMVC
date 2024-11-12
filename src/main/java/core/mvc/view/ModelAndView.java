package core.mvc.view;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public class ModelAndView {
    // 필요한 정보만 Map<key, value>에 저장하여 전달
    View view;
    Map<String, Object> model = new HashMap<>();

    public ModelAndView(View view) {
        // view만 초기화
        this.view = view;
    }

    // model에 데이터를 추가하는 메서드
    public ModelAndView addObject(String attributeName, Object attributeValue) {
        model.put(attributeName, attributeValue);
        return this; // 메서드 체이닝을 위해 자기자신의 객체를 반환
    }

    public void render(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        view.render(model, req, resp);
    }
}
