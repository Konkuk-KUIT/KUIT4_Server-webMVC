package controller;

import core.db.MemoryUserRepository;
import jwp.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static constants.URL.USER_LIST;

public class UpdateUserController implements Controller {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String userId = req.getParameter("userId");                   // userId는 변경하지 않음
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String email = req.getParameter("email");

        User updateUser = new User(userId, password, name, email);
        MemoryUserRepository.getInstance().changeUserInfo(updateUser);       // 사용자 정보 업데이트

        return "redirect:"+USER_LIST.getUrl();                               // redirect userList 로
    }
}
