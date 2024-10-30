package jwp.controller;

import core.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class QuestionFormController implements Controller {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        HttpSession session = req.getSession();
        // 세션에서 로그인 여부 확인
        if (session.getAttribute("user") == null) {
            return "redirect:/user/loginForm";
        }
        // 로그인된 경우, 질문 작성 폼 페이지로 이동
        return "/qna/form.jsp";
    }
}
