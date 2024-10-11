package controller;

import core.db.MemoryUserRepository;
import jwp.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Collection;

import static constants.URL.LIST_JSP;
import static constants.URL.LOGIN;

public class ListUserController implements Controller {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        HttpSession session = req.getSession();
        Object value = session.getAttribute("user");        // 세션에 저장된 정보 가져오기

        //세션에 정보 있음 = 로그인 상태
        if (value != null) {
            Collection<User> users = MemoryUserRepository.getInstance().findAll();
            req.setAttribute("users", users);

            return LIST_JSP.getUrl();               // forward
        }

        //로그인 상태 아님
        return "redirect:" + LOGIN.getUrl();        // redirect
    }
}
