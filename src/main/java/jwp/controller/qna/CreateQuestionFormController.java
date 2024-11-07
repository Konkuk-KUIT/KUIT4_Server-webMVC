package jwp.controller.qna;

import core.mvc.AbstractController;
import core.mvc.modelandview.ModelAndView;
import core.mvc.view.JspView;
import jwp.util.UserSessionUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CreateQuestionFormController extends AbstractController {

    @Override
    public ModelAndView execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        HttpSession session = req.getSession();
        if (UserSessionUtils.isLogined(session)) {          // 회원만 질문 등록 가능
            return jspView("/qna/form.jsp");
        }

        return jspView();
    }

    @Override
    protected ModelAndView jspView() {
        return new ModelAndView(new JspView("redirect:/user/loginForm"));
    }

    @Override
    protected ModelAndView jsonView() {
        return null;
    }
}
