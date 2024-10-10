package controller;

import core.db.MemoryUserRepository;
import jwp.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static controller.constant.SessionKey.*;
import static controller.constant.URI.*;

public class UpdateUserFormController implements Controller {

    protected MemoryUserRepository memoryUserRepository;

    public UpdateUserFormController(MemoryUserRepository memoryUserRepository) {
        this.memoryUserRepository = memoryUserRepository;
    }

    @Override
    public String process(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = memoryUserRepository.findUserById(req.getParameter("userId"));
        // 세션에 저장된 정보 가져오기
        HttpSession session = req.getSession();
        Object value = session.getAttribute(USER_SESSION_KEY.getKey());

        if (isValidSession(value, resp)) { return ROOT.getRedirectURI();}

        User loginUser = (User) value;

        if(isCheckUser(loginUser, user)) {
            //로그인 되어있는 user와 같은 user의 수정버튼 클릭
            req.setAttribute("user", user);
            return UPDATE_FORM.getJSPPath();
        }
        return USER_LIST.getRedirectURI();
    }

    private boolean isCheckUser(User loginUser, User user) {
        return loginUser.getUserId().equals(user.getUserId());
    }

    private boolean isValidSession(Object value, HttpServletResponse resp) throws IOException {
        if (value == null) {
            //로그인하지 않았는데 UserList에 접근한 상황
            System.out.println("잘못된 접근권한입니다!");
            return true;
        }
        return false;
    }

}
