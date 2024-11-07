package core.mvc.view;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class JsonView implements View{          //req에 있는 모든 파라미터를 map 자료구조에 저장, map 자료구조에 있는 모든 데이터들을 json형식으로 변환하여 사용자에게 응답
    @Override
    public void render(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {   //map 자료구조에 있는 모든 데이터들을 json형식으로 변환하여 사용자에게 http resp 응답
        ObjectMapper objectMapper = new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.print(objectMapper.writeValueAsString(model));          //model을 json으로 변환
    }

//    private static Map<String, Object> createModel(HttpServletRequest request) {                        //req에 있는 모든 파라미터를 map 자료구조에 저장후 리턴 (json으로 변환하기 위한 형식)
//        Enumeration<String> names = request.getParameterNames();
//        Map<String, Object> model = new HashMap<>();
//        while (names.hasMoreElements()) {
//            String name = names.nextElement();
//            model.put(name, request.getParameter(name));
//        }
//        return model;
//    }

}
