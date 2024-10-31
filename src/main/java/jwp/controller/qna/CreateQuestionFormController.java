package jwp.controller.qna;

import core.mvc.Controller;
import jwp.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateQuestionFormController implements Controller {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        User user = (User) req.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/user/login";
        }
        return "/qna/form.jsp";
    }
}
