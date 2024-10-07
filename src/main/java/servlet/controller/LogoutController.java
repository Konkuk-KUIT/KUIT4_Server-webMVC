package servlet.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class LogoutController implements Controller {

    @Override
    public String execute(HttpServletRequest req) {

        //세션 데이터 삭제
        HttpSession session = req.getSession();
        session.removeAttribute("user");
        System.out.println("로그아웃 성공");
        return "redirect:/";
    }

}
