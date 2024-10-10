package controller;

import core.db.MemoryUserRepository;
import jwp.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static constants.AttributeKey.ATTRIBUTE_KEY;
import static constants.QueryKey.USER_ID;
import static constants.RequestURI.*;
import static constants.SessionKey.SESSION_KEY;

public class UpdateUserFormController implements Controller {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        Object value = session.getAttribute(SESSION_KEY.getSessionKey());
        User loggedUser = (User) value;

        String userId = req.getParameter(USER_ID.getQueryKey());
        User user = MemoryUserRepository.getInstance().findUserById(userId);

        if (loggedUser.equals(user)) {
            req.setAttribute(ATTRIBUTE_KEY.getAttributeKey(), user);
            return UPDATE_FORM_JSP.getUri();
        }
        return USER_LIST.getRedirectUri();
    }
}
