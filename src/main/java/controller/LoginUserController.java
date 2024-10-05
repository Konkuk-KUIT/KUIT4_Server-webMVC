package controller;

import core.db.MemoryUserRepository;
import jwp.model.User;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LoginUserController implements Controller {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MemoryUserRepository memoryUserRepository = MemoryUserRepository.getInstance();
        User user = memoryUserRepository.findUserById(req.getParameter("userId"));

        if (user != null && user.getPassword().equals(req.getParameter("password"))) {
            HttpSession session = req.getSession();
            session.setAttribute("user", user);

            return "redirect:/";
        } else {
            return "/user/login_failed.jsp";
        }
    }



    /*@Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MemoryUserRepository memoryUserRepository = MemoryUserRepository.getInstance();
        User user = memoryUserRepository.findUserById(req.getParameter("userId"));

        if (user != null && user.getPassword().equals(req.getParameter("password"))) {
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
            resp.sendRedirect("/index.jsp");
        } else {
            RequestDispatcher rd = req.getRequestDispatcher("/user/login_failed.jsp");
            rd.forward(req, resp);
        }
    }*/
}
