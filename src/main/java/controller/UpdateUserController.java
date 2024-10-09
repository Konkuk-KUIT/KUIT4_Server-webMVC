package controller;

import jwp.model.User;
import core.db.MemoryUserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class UpdateUserController implements Controller {

    private static final String USER_SESSION_KEY = "user";
    private final MemoryUserRepository userRepository = MemoryUserRepository.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        User loggedInUser = (User) session.getAttribute(USER_SESSION_KEY);

        if (loggedInUser == null) {
            return "redirect:/user/login.jsp";
        }

        String userId = request.getParameter("userId");

        // 로그인한 사용자의 ID와 수정하려는 사용자의 ID가 일치하는지 확인
        if (!loggedInUser.getUserId().equals(userId)) {
            request.setAttribute("error", "자신의 정보만 수정할 수 있습니다.");
            return "/user/updateForm.jsp";
        }

        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String email = request.getParameter("email");

        // 유저 정보 객체 생성 및 업데이트
        User updatedUser = new User(userId, password, name, email);
        userRepository.changeUserInfo(updatedUser);

        // 세션의 사용자 정보도 업데이트
        session.setAttribute(USER_SESSION_KEY, updatedUser);

        // 업데이트 후, 사용자 목록으로 리다이렉트
        return "redirect:/user/userList";
    }
}