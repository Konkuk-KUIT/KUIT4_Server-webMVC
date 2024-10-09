package jwp.controller;

import core.db.MemoryUserRepository;
import jwp.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static jwp.constant.Path.LIST;
import static jwp.constant.Path.UPDATE_FORWARD;
import static jwp.constant.UserQueryKey.ID;

public class UpdateUserFormController implements Controller {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        HttpSession session = req.getSession();
        Object value = session.getAttribute("user");
        User loginedUser = (User) value;
        String userId = req.getParameter(ID.getQueryKey());
        User user = MemoryUserRepository.getInstance().findUserById(userId);
        if (loginedUser.equals(user)) {
            req.setAttribute("users", user);
            return UPDATE_FORWARD.getPath();
        }
        return LIST.getRedirectPath();
    }
}
