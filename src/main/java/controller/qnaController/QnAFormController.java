package controller.qnaController;

import controller.Controller;
import jwp.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class QnAFormController implements Controller {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        HttpSession session = req.getSession();
        Object value = session.getAttribute("user");
        //여기서 USER_SESSION_KEY는 사용자 정보를 세션에 저장할 때 사용한 키를 나타내는  변수이다!

        //내용 토대로 create 해보기..?
        if (value != null) {
            User user = (User) value;
            req.setAttribute("user",user);
            return "/qna/form.jsp";
        }
        return "redirect:/user/login.j" +
                "sp";
    }
}
