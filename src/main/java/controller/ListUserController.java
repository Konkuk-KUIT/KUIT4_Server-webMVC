package controller;

import core.db.MemoryUserRepository;
import jwp.model.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

public class ListUserController implements Controller{

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if(req.getSession().getAttribute("user") != null){
            Collection<User> users = MemoryUserRepository.getInstance().findAll();
            req.setAttribute("users", users);

            return "/user/list.jsp";
        }
        else{
            System.out.println("유저 리스트 로딩 실패");

            return "redirect:/";
        }

    }

}
