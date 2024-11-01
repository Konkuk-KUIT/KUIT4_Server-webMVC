package jwp.controller;

import core.mvc.Controller;
import jwp.dao.QuestionDao;
import jwp.model.Question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class CreateQuestionFormController implements Controller {


    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        HttpSession session = req.getSession();
        Object value = session.getAttribute("user");
        //세션의 유저 아이디: value
        //회원인지 확인
        if(value == null){
            return "/qna/form.jsp";
        }
        //회원아니묜 다시 로그인
        return "redirect:/user/loginForm";
    }
}
