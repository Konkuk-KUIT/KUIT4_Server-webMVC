package servlet.controller;

import core.db.MemoryUserRepository;
import jwp.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


public class LoginController implements Controller {

    private final  MemoryUserRepository memoryUserRepository;

    public LoginController(MemoryUserRepository memoryUserRepository) {
        this.memoryUserRepository = memoryUserRepository;
    }

    @Override
    public String execute(HttpServletRequest req) {

        if(req.getMethod().equals("GET")) {
            return "/user/login.jsp";
        }

        User user = memoryUserRepository.findUserById(req.getParameter("userId"));
        //해당하는 아이디없으면 || 비밀번호 틀리면 로그인실패
        if(user == null || !(user.getPassword().equals(req.getParameter("password")))) {
            System.out.println("로그인 실패");
            return "redirect:/user/login_failed.jsp";
        }
        //로그인 성공
        //세션 정보 저장
        System.out.println("로그인 성공");
        HttpSession session = req.getSession();
        session.setAttribute("user", user);
        return "redirect:/";
    }

}
