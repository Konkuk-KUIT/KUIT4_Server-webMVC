package jwp.controller.qna;

import core.mvc.JspController;
import core.mvc.view.JspView;
import core.mvc.view.ModelAndView;
import core.mvc.view.View;
import jwp.util.UserSessionUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CreateQuestionFormController implements JspController {

    @Override
    public ModelAndView execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        HttpSession session = req.getSession();
        if (UserSessionUtils.isLogined(session)) {          // 회원만 질문 등록 가능
            return jspView("/qna/form.jsp");
        }
        return jspView("redirect:/user/loginForm");
    }


    @Override
    public ModelAndView jspView(String viewname) {
        final View view = new JspView(viewname);
        final ModelAndView modelAndView = new ModelAndView(view);
        return modelAndView;
    }
}
