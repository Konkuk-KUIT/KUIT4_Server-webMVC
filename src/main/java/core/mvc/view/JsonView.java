package core.mvc.view;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class JsonView implements View{



    private static Map<String, Object> createModel(HttpServletRequest request) {
        Enumeration<String> names= request.getParameterNames();
        Map<String, Object> model=new HashMap<>();
        while(names.hasMoreElements()){
            String name = names.nextElement();
            model.put(name, request.getParameter(name));
        }
        return model;
    }

    @Override
    public void render(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
        //req에 있는 para 가져와서 map 저장 및 json으로 변환
        //ctrl+shift+alt+T =  메소드 추출
        ObjectMapper objectMapper = new ObjectMapper();
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.print(objectMapper.writeValueAsString(createModel(request)));

    }
}
