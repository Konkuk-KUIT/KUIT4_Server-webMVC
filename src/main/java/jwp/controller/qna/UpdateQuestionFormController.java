package jwp.controller.qna;

import core.mvc.Controller;
import core.mvc.JspController;
import core.mvc.view.JspView;
import core.mvc.view.ModelAndView;
import core.mvc.view.View;
import jwp.dao.QuestionDao;
import jwp.model.Question;
import jwp.model.User;
import jwp.util.UserSessionUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class UpdateQuestionFormController implements JspController {

    private final QuestionDao questionDao = new QuestionDao();

    @Override
    public ModelAndView execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        HttpSession session = req.getSession();
        if (!UserSessionUtils.isLogined(session)) {          // 회원만 질문 등록 가능
            return jspView("redirect:/user/loginForm");
        }
        String questionId = req.getParameter("questionId");
        Question question = questionDao.findByQuestionId(Integer.parseInt(questionId));
        User user = UserSessionUtils.getUserFromSession(session);
        if (!question.isSameUser(user)) {
            throw new IllegalArgumentException();
        }
        return jspView("/qna/updateForm.jsp")
                .addObject("question", question);
    }

    @Override
    public ModelAndView jspView(String viewname) {
        final View view = new JspView(viewname);
        final ModelAndView modelAndView = new ModelAndView(view);
        return modelAndView;
    }

}
