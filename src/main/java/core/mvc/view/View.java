package core.mvc.view;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public interface View {
    //사용자에게 페이지 보여주는 메서드
    //여기 model값도 같이 넘길 수 있도록 수정

    void render(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception;
}
