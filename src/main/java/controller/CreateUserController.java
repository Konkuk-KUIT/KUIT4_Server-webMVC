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

        return FORM_JSP.getUrl();      // forward (회원가입 페이지 출력)   front 측에 해당 매커니즘을 전달해줘야 됨 
    }                                  // (front측과 올바른지 않은 방법으로 의사소통할 가능성이 있음)
}                                      // 그래서 해당 경로로 접근하는 방법이 없는게 맞음, get*post에 따른 컨트롤러 따로 구현해 주는게 맞음