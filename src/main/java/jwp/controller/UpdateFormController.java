package jwp.controller;

import core.mvc.Controller;
import jwp.dao.QuestionDao;
import jwp.model.Question;
import jwp.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateFormController implements Controller {

    private final QuestionDao questionDao = new QuestionDao();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        User user = (User) req.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/user/login";
        }

        int questionId = Integer.parseInt(req.getParameter("questionId"));
        Question question = questionDao.findByQuestionId(questionId);

        if (!user.getUserId().equals(question.getWriter())) {
            throw new IllegalArgumentException("수정 권한이 없습니다.");
        }

        req.setAttribute("question", question);
        return "/qna/updateForm.jsp";
    }
}
