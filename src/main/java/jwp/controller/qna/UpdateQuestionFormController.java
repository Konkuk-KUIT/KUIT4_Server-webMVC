package jwp.controller.qna;

import core.mvc.AbstractController;
import core.mvc.modelandview.ModelAndView;
import jwp.dao.QuestionDao;
import jwp.model.Question;
import jwp.model.User;
import jwp.util.UserSessionUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class UpdateQuestionFormController extends AbstractController {

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

        return jspView("/qna/updateForm.jsp").addObject("question", question);
    }

    @Override
    protected ModelAndView jspView() {
        return null;
    }

    @Override
    protected ModelAndView jsonView() {
        return null;
    }
}
