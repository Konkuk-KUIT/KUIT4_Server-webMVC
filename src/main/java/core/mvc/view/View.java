package core.mvc.view;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public interface View {
    void render(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception;
}
// 여기서 render의 인자로 model 관련을 넣어야 함 -> ModelAndView에서 view.render에서 현재 오류 발생
