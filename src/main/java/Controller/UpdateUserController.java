package Controller;

import core.db.MemoryUserRepository;
import jwp.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateUserController implements Controller {

    @Override
    public String handleRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 회원 정보 업데이트 처리
        User updateUser = new User(req.getParameter("userId"),
                req.getParameter("password"),
                req.getParameter("name"),
                req.getParameter("email"));
        MemoryUserRepository.getInstance().changeUserInfo(updateUser);
        System.out.println("회원정보 수정 완료");
        return "redirect:/user/userList"; // 업데이트 후 유저 목록으로 리다이렉트
    }
}
