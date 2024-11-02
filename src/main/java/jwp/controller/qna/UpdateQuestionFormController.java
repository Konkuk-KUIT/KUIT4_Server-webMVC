package jwp.controller.qna;

import core.mvc.Controller;
import core.mvc.view.JspView;
import core.mvc.view.View;
import jwp.dao.QuestionDao;
import jwp.model.Question;
import jwp.model.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UpdateQuestionFormController implements Controller {
    private final QuestionDao questionDao = new QuestionDao();

    @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        HttpSession session = req.getSession();
        User loginUser = (User) session.getAttribute("user");

        if (loginUser == null) {
            return new JspView("redirect:/user/loginForm");
        }

        String questionId = req.getParameter("questionId");
        Question question = questionDao.findByQuestionId(Integer.parseInt(questionId));

        if (!loginUser.getUserId().equals(question.getWriter())) {
            throw new IllegalArgumentException("자신의 글만 수정할 수 있습니다.");
        }

        req.setAttribute("question", question);
        return new JspView("/qna/updateForm.jsp");
    }
}
