package controller;

import controller.constant.SessionKey;
import controller.constant.URI;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static controller.constant.SessionKey.USER_SESSION_KEY;
import static controller.constant.URI.ROOT;

public class LogOutController implements Controller {

    @Override
    public String process(HttpServletRequest req, HttpServletResponse resp) {
        //세션 데이터 삭제
        HttpSession session = req.getSession();
        session.removeAttribute(USER_SESSION_KEY.getKey());
        return ROOT.getRedirectURI();
    }
}
