package jwp.controller;

import core.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class QnaFormController implements Controller {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        if (req.getSession().getAttribute("user") == null) {
            return "redirect:/user/login";
        }
        return "/qna/form.jsp";
    }
}

