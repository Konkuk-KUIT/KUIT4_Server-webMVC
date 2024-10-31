package Controller.qna;

import Controller.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateQuestionFormController implements Controller {

    @Override
    public String handleRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // 세션에서 유저 정보 확인
        Object user = req.getSession().getAttribute("user");
        if (user == null) {
            // 로그인되지 않은 경우 로그인 페이지로 리다이렉트
            return "redirect:/user/login.jsp";
        }

        // 로그인된 경우, 질문 작성으로 이동
        return "/qna/form.jsp";
    }
}
