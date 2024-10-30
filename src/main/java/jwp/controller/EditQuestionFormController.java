package jwp.controller;

import core.mvc.Controller;
import jwp.dao.QuestionDao;
import jwp.model.Question;
import jwp.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;

public class EditQuestionFormController implements Controller {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        HttpSession session = req.getSession();
        User user = (User)session.getAttribute("user");
        String questionId = req.getParameter("questionId");

        QuestionDao questionDao = new QuestionDao();
        Question foundQuestion = questionDao.findById(questionId);
        if(foundQuestion != null && !Objects.equals(foundQuestion.getWriter(), user.getUserId())) {
            throw new IllegalArgumentException();
        }

        req.setAttribute("question", foundQuestion);
        return "/qna/edit.jsp";
    }
}
