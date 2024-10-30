package jwp.controller.qna;

import core.mvc.Controller;
import jwp.util.UserSessionUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ViewQuestionController implements Controller {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession();
        if(! UserSessionUtils.isLogined(session)) {
            return "redirect:/user/login";
        }
        return "/qna/form.jsp";
    }

}