package controller;

import core.db.MemoryUserRepository;
import jwp.model.User;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static constants.URL.*;

public class CreateUserController extends HttpServlet implements Controller {
    @Override       //회원가입 진행
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        if (req.getMethod().equalsIgnoreCase("POST")) {
            User user = new User(req.getParameter("userId"),
                    req.getParameter("password"),
                    req.getParameter("name"),
                    req.getParameter("email"));

            MemoryUserRepository.getInstance().addUser(user);
            return "redirect:"+ROOT.getUrl();            // redirect
        }

        return FORM_JSP.getUrl();      // forward (회원가입 페이지 출력)
    }
}