package core.mvc.view;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Map;

public class JsonView implements View{
    @Override
    public void render(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        // 모델에 담긴 정보들을 JSON으로 변환하여 사용자에게 전달
        // out.print(objectMapper.writeValueAsString(createModel(request)));
        out.print(objectMapper.writeValueAsString(model));
    }

//    private static Map<String, Object> createModel(HttpServletRequest request) {
//        // Request에 있는 모든 Parameter들의 이름을 가져옴
//        Enumeration<String> names = request.getParameterNames();
//
//        // Map 자료구조(Model)에 모든 Parameter들을 저장
//        Map<String, Object> model = new HashMap<>();
//        while (names.hasMoreElements()) {
//            String name = names.nextElement();
//            model.put(name, request.getParameter(name));
//        }
//        return model;
//    }

}
