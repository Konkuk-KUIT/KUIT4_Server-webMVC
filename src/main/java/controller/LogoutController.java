package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static constants.URL.ROOT;

public class LogoutController implements Controller {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp){
        HttpSession session = req.getSession(false);   // 현재 세션 가져오기 (있을 때만)

        if (session != null) {
            session.removeAttribute("user");            // 세션 삭제 (로그아웃 처리)
        }

        return "redirect:"+ROOT.getUrl();                     // redirect
    }
}
