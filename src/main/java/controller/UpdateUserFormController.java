package controller;

import core.db.MemoryUserRepository;
import jwp.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Collection;

@WebServlet("/user/updateForm")
public class UpdateUserFormController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();
        Object value = session.getAttribute("user");
        if (value != null) {
            User getSessionUser = (User) value;
            //이거랑 일치하는 사람만 updateForm 보내게
            User user =MemoryUserRepository.getInstance().findUserById(getSessionUser.getUserId());
            if(user.getUserId().equals(req.getParameter("userId"))){
                //들어온 아이디랑 세션이랑 같은 경유만 실행
                req.setAttribute("user",user);
                RequestDispatcher rd =req.getRequestDispatcher("/user/updateForm.jsp");
                rd.forward(req,resp);
            }
            resp.sendRedirect("/user/userList");



        }



    }
}
