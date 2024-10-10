package controller.get;

import controller.Controller;
import core.db.MemoryUserRepository;
import jwp.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static enums.Route.USER_LIST;
import static enums.UserQueryKey.USERID;
import static enums.ViewPath.FORM_JSP;

public class UpdateUserFormController implements Controller {

    @Override
    public String handleRequest(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        User sessionUser = (User) session.getAttribute("user");
        String sessionUserId = sessionUser.getUserId();

        String parameterUserId = req.getParameter(USERID.getValue());

        if(sessionUserId != null && !sessionUserId.equals(parameterUserId)){
            return USER_LIST.getRedirectCommand();
        }

        User user = MemoryUserRepository.getInstance().findUserById(sessionUserId);
        req.setAttribute("user", user);
        return FORM_JSP.getPath();
    }
}
