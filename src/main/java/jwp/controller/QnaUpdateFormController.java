package jwp.controller;

import core.mvc.Controller;
import jwp.dao.QuestionDao;
import jwp.model.Question;
import jwp.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class QnaUpdateFormController implements Controller {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        QuestionDao questionDao = new QuestionDao();
        int questionId = Integer.parseInt(req.getParameter("questionId"));
        Question question = questionDao.findByQuestionId(questionId);
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (!question.getWriter().equals(user.getName())) {
            throw new IllegalArgumentException();
        }
        req.setAttribute("question", question);
        return "/qna/updateForm.jsp";
    }
}
