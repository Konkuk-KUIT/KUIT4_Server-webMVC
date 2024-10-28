package jwp.controller;

import core.mvc.Controller;
import jwp.dao.QuestionDao;
import jwp.model.Question;
import jwp.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UpdateQuestionFormController implements Controller {

    QuestionDao questionDao = new QuestionDao();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        HttpSession session = req.getSession();
        Long questionId = Long.parseLong(req.getParameter("questionId"));
        Question findQuestion = questionDao.findByQuestionId(questionId);

        User loginedUser = (User) session.getAttribute("user");

        if(!findQuestion.getWriter().equals(loginedUser.getName())){
            throw new IllegalArgumentException();
        }

        req.setAttribute("question", findQuestion);
        return "/qna/updateForm.jsp";
    }
}
