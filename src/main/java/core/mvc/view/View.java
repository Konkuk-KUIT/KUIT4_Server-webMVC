package core.mvc.view;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public interface View {
    void render(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception;     //사용자에게 웹 페이지를 보여주는 함수
}
