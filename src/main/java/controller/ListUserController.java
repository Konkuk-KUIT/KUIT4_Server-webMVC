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
import java.awt.*;
import java.io.IOException;
import java.util.Collection;

public class ListUserController implements Controller {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        HttpSession session = req.getSession();
        Object value = session.getAttribute("user");
        //여기서 USER_SESSION_KEY는 사용자 정보를 세션에 저장할 때 사용한 키를 나타내는  변수이다!
        if (value != null) {
            User user = (User) value;
            //이건 Update쪽에서 쓰면 될 듯

            Collection<User> users= MemoryUserRepository.getInstance().findAll();
            req.setAttribute("users",users);

//            RequestDispatcher rd =req.getRequestDispatcher("/user/list.jsp");
//            rd.forward(req,resp);

            return "/user/list.jsp";
        }
        //resp.sendRedirect("/user/login.jsp");
        return "redirect:/user/login.jsp";
    }
}
