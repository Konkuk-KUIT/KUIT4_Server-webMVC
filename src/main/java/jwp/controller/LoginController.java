package jwp.controller;

import core.db.MemoryUserRepository;
import core.mvc.Controller;
import jwp.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginController implements Controller {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // 고유객체 생성 혹은 존재하는 세션반환
        // 다른 페이지 요청에서도 세션을 통해 같은 사용자임을 식별
        HttpSession session = req.getSession();
        String userId = req.getParameter("userId");
        String password = req.getParameter("password");

        User loginUser = new User(userId, password);
        User user = MemoryUserRepository.getInstance().findUserById(userId);

        if (user != null && user.isSameUser(loginUser)) {
            // 로그인 성공하면 세션 저장
            session.setAttribute("user", user);
            return "redirect:/";
        }
        return "redirect:/user/loginFailed";
    }
}