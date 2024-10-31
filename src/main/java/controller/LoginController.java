package controller;

import jwp.dao.MemoryUserRepository;
import jwp.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginController  implements Controller {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String userId = req.getParameter("userId");
        String password = req.getParameter("password");
        User user = MemoryUserRepository.getInstance().findUserById(userId);
        //유저찾기
        //근데 로그인 에서만 로그아웃 혹은 그렇게 보이게 하는지 아니면 메인 전체 다 그렇게 보이게하는지
        if (user != null && user.getPassword().equals(password)) {
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
            //resp.sendRedirect("/"); // 로그인 후 메인화면
            return "redirect:/";

        }
        RequestDispatcher rd = req.getRequestDispatcher("/user/login_failed.jsp");
        rd.forward(req, resp);
        return "/user/login_failed.jsp";


    }
}
