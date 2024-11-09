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

import static constants.HttpMethod.*;
import static constants.QueryKey.*;
import static constants.RequestURL.*;

public class CreateUserController extends HttpServlet implements Controller {

    Repository repository;

    public CreateUserController(Repository repository) {
        this.repository = repository;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {

        if(req.getMethod().equals(GET.getMethod())) {
            // GET 방식으로 요청이 들어올 경우 form.jsp 파일로 forward하도록 url 반환
            return FORM_JSP.getUrl();
        }

        User user = new User(req.getParameter(ID.getKey()),
                req.getParameter(PASSWORD.getKey()),
                req.getParameter(NAME.getKey()),
                req.getParameter(EMAIL.getKey()));

        repository.addUser(user);

        // 회원 가입이 완료되고 나면 홈 화면으로 redirect
        return "redirect:/";
    }
}
