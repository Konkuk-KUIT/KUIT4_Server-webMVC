package jwp.controller.question;

import core.mvc.Controller;
import jwp.dao.QuestionDao;
import jwp.model.Question;
import jwp.model.User;
import jwp.util.UserSessionUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UpdateQuestionFormController implements Controller {
    private final QuestionDao questionDao;

    public UpdateQuestionFormController(QuestionDao questionDao) {
        this.questionDao = questionDao;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        HttpSession session = req.getSession();
        User user = UserSessionUtils.getUserFromSession(session);
        // 로그인되지 않은 상태이면, 로그인 화면으로 리다이렉트
        if (!UserSessionUtils.isLogined(session) || user == null) {
            return "redirect:/users/loginForm";
        }

        String questionId = req.getParameter("questionId");
        Question question = questionDao.findByQuestionId(Integer.parseInt(questionId));

        // 사용자가 질문의 작성자가 아닌 경우
        if (!user.isWriterOf(question)) {
            throw new IllegalArgumentException();
        }

        req.setAttribute("question", question);
        return "/qna/updateForm.jsp";
    }
}
