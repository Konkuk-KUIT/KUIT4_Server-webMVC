package controller;

import constants.SessionKey;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static constants.RequestURI.ROOT;
import static constants.SessionKey.*;

public class LogoutController implements Controller {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        session.removeAttribute(SESSION_KEY.getSessionKey());
        System.out.println("user 로그아웃 성공");
        return ROOT.getRedirectUri();
    }
}
