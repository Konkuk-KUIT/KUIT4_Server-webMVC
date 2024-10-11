package controller;

import core.db.MemoryUserRepository;
import jwp.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static constants.URL.*;

public class LoginController implements Controller {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp){
        //로그인되지 않은 상태에서 list 접근 시 처리 위함
        if (req.getMethod().equalsIgnoreCase("GET")) {
            return LOGIN_JSP.getUrl();              // forward
        }

        String userId = req.getParameter("userId");
        String password = req.getParameter("password");

        User user = MemoryUserRepository.getInstance().findUserById(userId);

        // 로그인 성공, 세션에 사용자 정보 저장
        if (user != null && user.matchPassword(password)) {
            HttpSession session = req.getSession();
            session.setAttribute("user", user);            // 세션에 사용자 정보 저장

            return "redirect:"+ROOT.getUrl();                    // redirect  (로그인 후 메인 페이지로)
        }

        // 로그인 실패
        return LOGIN_FAILED_JSP.getUrl();                        // forward
    }
}
