package controller.post;

import controller.Controller;
import core.db.MemoryUserRepository;
import jwp.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static enums.Route.HOME;
import static enums.UserQueryKey.*;
import static enums.ViewPath.LOGIN_FAILED_JSP;

public class LoginController implements Controller {

    @Override
    public String handleRequest(HttpServletRequest req, HttpServletResponse resp) {
        String userId = req.getParameter(USERID.getValue());
        String password = req.getParameter(PASSWORD.getValue());

        boolean isLoginSuccessed = false;
        User user = MemoryUserRepository.getInstance().findUserById(userId);
        if(user != null && user.getPassword().equals(password)) isLoginSuccessed = true;

        if(isLoginSuccessed){
            // 세션 정보 저장
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
            return HOME.getRedirectCommand();
        }
        return LOGIN_FAILED_JSP.getPath();
    }
}
