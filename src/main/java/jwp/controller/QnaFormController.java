package jwp.controller;

import core.mvc.Controller;
import jwp.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class QnaFormController implements Controller {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        User user = (User) req.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/user/login";
        }

        String userId = user.getUserId();
        req.setAttribute("userId", userId);

        return "/qna/form.jsp";
    }
}
