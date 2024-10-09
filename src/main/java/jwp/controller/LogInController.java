package jwp.controller;

import core.db.MemoryUserRepository;
import jwp.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static jwp.constant.Path.LOGIN_FAILED;
import static jwp.constant.Path.ROOT;
import static jwp.constant.UserQueryKey.ID;
import static jwp.constant.UserQueryKey.PWD;

public class LogInController implements Controller {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String userId = req.getParameter(ID.getQueryKey());
        String password = req.getParameter(PWD.getQueryKey());
        User user = MemoryUserRepository.getInstance().findUserById(userId);

        if (user != null) {
            if (isLoginedUser(password, user)) {
                HttpSession session = req.getSession();
                session.setAttribute("user", user);
                return ROOT.getRedirectPath();
            }
        }
        return LOGIN_FAILED.getRedirectPath();
    }

    private boolean isLoginedUser(String password, User user) {
        return password.equals(user.getPassword());
    }
}
