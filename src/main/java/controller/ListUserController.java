package controller;

import core.db.MemoryUserRepository;
import jwp.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Collection;

import static constants.AttributeKey.ATTRIBUTE_KEY;
import static constants.RequestURI.*;
import static constants.SessionKey.*;

public class ListUserController implements Controller {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        Object value = session.getAttribute(SESSION_KEY.getSessionKey());

        if (value != null) {
            Collection<User> users = MemoryUserRepository.getInstance().findAll();
            req.setAttribute(ATTRIBUTE_KEY.getAttributeKey(), users);
            return USER_LIST_JSP.getUri();
        }
        return LOGIN_JSP.getRedirectUri();
    }
}
