package controller;

import core.db.MemoryUserRepository;
import jwp.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collection;

// 로그인 -> main page : UserList 출력은 UserList 버튼 눌렀을 때만 가능함

public class ListUserController implements Controller {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {

        // 세션에 저장된 정보 가져오기
        HttpSession session = request.getSession();
        User loggedInUser = (User) session.getAttribute("user");

        // 로그인 상태가 아닌 경우 login 하도록 redirect
        if (loggedInUser == null) {
            return "redirect:/user/login.jsp";
        }

        // 로그인 된 상태인 경우
        // 출력할 User들의 정보를 받아 list.jsp에 넘겨주기
        Collection<User> users = MemoryUserRepository.getInstance().findAll();
        request.setAttribute("users", users);

        // Request Dispatcher (준비 -> 실행가능) + forward (클라이언트 요청 -> 다른 servlet or jsp로 전달)
        // RequestDispatcher rd = request.getRequestDispatcher("/user/list.jsp");
        // rd.forward(request, resp);

        return "/user/list.jsp";
    }
}
