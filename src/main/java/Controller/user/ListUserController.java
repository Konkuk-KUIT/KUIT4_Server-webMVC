package Controller.user;

import Controller.Controller;
import core.db.MemoryUserRepository;
import jwp.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collection;

public class ListUserController implements Controller {

    @Override
    public String handleRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 세션에 저장된 정보 가져오기
        HttpSession session = req.getSession();
        Object value = session.getAttribute("user");

        if (value != null) {
            User loggedInUser = (User) value;

            // 전체 유저 목록 가져오기
            Collection<User> users = MemoryUserRepository.getInstance().findAll();
            req.setAttribute("users", users);

            // 반환할 뷰 이름 (forward할 JSP 파일의 이름)
            return "/user/list.jsp";
        } else {
            return "redirect:/"; // 세션 정보가 없으면 메인 페이지로 리다이렉트
        }
    }
}
