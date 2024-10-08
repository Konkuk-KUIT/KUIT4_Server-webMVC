package jwp.controller;

import core.db.MemoryUserRepository;
import jwp.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogInController implements Controller {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String userId = req.getParameter("userId");
        String password = req.getParameter("password");
        User user = MemoryUserRepository.getInstance().findUserById(userId);

        if (user != null) {
            if (isLoginedUser(password, user)) {
                HttpSession session = req.getSession();
                session.setAttribute("user", user);
                return "redirect:/";
            }
        }
        return "redirect:/user/login_failed.jsp";
    }

    private boolean isLoginedUser(String password, User user) {
        return password.equals(user.getPassword());
    }
}
