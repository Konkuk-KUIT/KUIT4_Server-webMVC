package core.controller;

import enums.URLPath;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListUserController implements Controller {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        // 요청 처리 로직
        String cookieHeader = request.getHeader("Cookie");
        if (cookieHeader == null || !(cookieHeader.contains("logined=true"))) {
            return "redirect:" + URLPath.INDEX.getPath();
        }
        return "/user/list.jsp"; // forward할 URL 반환
    }
}
