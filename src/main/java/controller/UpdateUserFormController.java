package controller;

import core.db.MemoryUserRepository;
import jwp.model.User;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateUserFormController implements Controller {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = MemoryUserRepository.getInstance().findUserById(req.getParameter("userId"));

        if (user != null) {
            req.setAttribute("users", user);
            return "/user/updateForm.jsp";
        }

        return "redirect:/user/list";
    }

    /*@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = MemoryUserRepository.getInstance().findUserById(req.getParameter("userId"));

        if (user != null) {
            req.setAttribute("users", user);
            RequestDispatcher rd = req.getRequestDispatcher("/user/updateForm.jsp");
            rd.forward(req, resp);
        }
    }*/
}
