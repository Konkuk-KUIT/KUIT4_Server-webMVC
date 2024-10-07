package controller;

import core.db.MemoryUserRepository;
import jwp.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Collection;

import static controller.constant.SessionKey.USER_SESSION_KEY;

public class ListUserController implements Controller {

    @Override
    public String process(HttpServletRequest req, HttpServletResponse resp) {
        // 세션에 저장된 정보 가져와서 Login 정보 체크
        if (isLogin(req.getSession())) {
            //로그인되어 있는 상태라면
            Collection<User> users = MemoryUserRepository.getInstance().findAll();
            req.setAttribute("users", users);

            //jsp에게 화면 출력에 대한 요청 처리
            return "/user/list.jsp";
        } else {
            //로그인이 되어있지 않은 상태
            return "/user/login.jsp";
        }
    }

    private boolean isLogin(HttpSession session) {
        return session.getAttribute(USER_SESSION_KEY.getKey()) != null;
    }
}
