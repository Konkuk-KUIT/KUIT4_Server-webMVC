package controller;

import core.db.MemoryUserRepository;
import core.db.Repository;
import jwp.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/user/updateForm")
public class UpdateUserFormController extends HttpServlet {

    Repository repository = MemoryUserRepository.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("userId");
        User findUser = repository.findUserById(userId);

        HttpSession session = req.getSession();
        Object value = session.getAttribute("user");

        // 로그인 하지 않은 상황에서는 해당 URL로 요청이 올 수 없기 때문에 검증 로직은 삭제
        User currentUser = (User) value;

        if(findUser.isSameUser(currentUser)){
            req.setAttribute("user", findUser);

            RequestDispatcher rd = req.getRequestDispatcher("/user/updateForm.jsp");
            rd.forward(req, resp);

            return;
        }

        resp.sendRedirect("/user/userList");


    }
}
