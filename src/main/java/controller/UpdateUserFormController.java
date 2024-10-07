package controller;

import core.db.MemoryUserRepository;
import jwp.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation .WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/user/update.jsp")
public class UpdateUserFormController extends HttpServlet {

    private final String USER_SESSION_KEY = "user";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = MemoryUserRepository.getInstance().findUserById(req.getParameter("userId"));
        // 세션에 저장된 정보 가져오기
        HttpSession session = req.getSession();
        Object value = session.getAttribute(USER_SESSION_KEY);

        checkSession(value, resp);

        User loginUser = (User) value;
        if(loginUser.getUserId().equals(user.getUserId())) {
            //로그인 되어있는 user와 같은 user의 수정버튼 클릭
            req.setAttribute("user", user);
            req.getRequestDispatcher("/user/updateForm.jsp").forward(req, resp);
            return;
        }

        resp.sendRedirect("/user/userList");
    }

    private void checkSession(Object value, HttpServletResponse resp) throws IOException {
        if (value == null) {
            //로그인하지 않았는데 UserList에 접근한 상황
            System.out.println("잘못된 접근권한입니다!");
            resp.sendRedirect("/");
        }
    }

}
