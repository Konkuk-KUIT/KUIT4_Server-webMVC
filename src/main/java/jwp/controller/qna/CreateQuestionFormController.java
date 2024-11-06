package jwp.controller.qna;

import core.mvc.Controller;
import core.mvc.modelandview.ModelAndView;
import core.mvc.view.JspView;
import core.mvc.view.View;
import jwp.util.UserSessionUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CreateQuestionFormController implements Controller {

    @Override
    public ModelAndView execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        HttpSession session = req.getSession();
        if (UserSessionUtils.isLogined(session)) {          // 회원만 질문 등록 가능
            return new ModelAndView(new JspView("/qna/form.jsp"));
        }

        return new ModelAndView(new JspView("redirect:/user/loginForm"));
    }
}
