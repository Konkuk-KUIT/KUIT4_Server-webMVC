package controller;

import core.db.MemoryUserRepository;
import jwp.model.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user/updateForm/*")
public class UpdateUserFormController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String pathInfo = req.getPathInfo();
        if(pathInfo != null && pathInfo.length() > 1){
            String userId = pathInfo.substring(1);

            User foundUser = MemoryUserRepository.getInstance().findUserById(userId);
            req.setAttribute("user", foundUser);
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("/user/updateForm.jsp");
        dispatcher.forward(req, resp);
    }
}
