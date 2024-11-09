package controller;

import constants.HttpMethod;
import constants.QueryKey;
import constants.RequestURL;
import core.db.MemoryUserRepository;
import core.db.Repository;
import jwp.model.User;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static constants.HttpMethod.*;
import static constants.QueryKey.*;
import static constants.RequestURL.*;


public class LoginController extends HttpServlet implements Controller{

    Repository repository;

    public LoginController(Repository repository) {
        this.repository = repository;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        if(req.getMethod().equals(GET.getMethod())) {
            // GET 방식으로 요청이 들어올 경우 JSP 파일 forward 하기 위한 url 반환
            return LOGIN_JSP.getUrl();
        }

        String userId = req.getParameter(ID.getKey());
        String password = req.getParameter(PASSWORD.getKey());

        User findUser = repository.findUserById(userId);

        if (findUser == null || !findUser.matchPassword(password)) {
            // 로그인에 실패하는 경우 login_failed.jsp 파일로 forward 하기 위한 url 반환
            return LOGIN_FAILED_JSP.getUrl();
        }

        HttpSession session = req.getSession();
        session.setAttribute("user", findUser);

        // 성공적으로 login 될 경우 홈 화면으로 redirect
        return "redirect:/";
    }
}