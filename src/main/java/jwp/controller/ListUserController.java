package jwp.controller;

import core.db.MemoryUserRepository;
import core.mvc.Controller;
import jwp.util.UserSessionUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ListUserController implements Controller {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        HttpSession session = req.getSession();
        if (UserSessionUtils.isLogined(session)) {
            // 모든 사용자 목록으로
            req.setAttribute("users", MemoryUserRepository.getInstance().findAll());
            return "/user/list.jsp";
        }
        return "redirect:/user/loginForm";
    }
}