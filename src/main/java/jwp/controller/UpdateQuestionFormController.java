package jwp.controller;

import core.mvc.Controller;
import jwp.dao.QuestionDao;
import jwp.model.Question;
import jwp.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UpdateQuestionFormController implements Controller {

    private final QuestionDao questionDao = new QuestionDao();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            return "redirect:/user/loginForm";
        }

        long questionId = Long.parseLong(req.getParameter("questionId"));
        Question question = questionDao.findByQuestionId(questionId);

        if (!question.getWriter().equals(user.getUserId())) {
            throw new IllegalArgumentException();
        }

        req.setAttribute("question", question);
        return "/qna/updateForm.jsp";
    }
}
