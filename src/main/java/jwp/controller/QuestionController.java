package jwp.controller;

import core.mvc.Controller;
import jwp.dao.QuestionDao;
import jwp.model.Question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class QuestionController implements Controller {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        HttpSession session = req.getSession();
        if (session != null) {
            Question question = (Question) session.getAttribute("question");
            if (question == null) {
                return "redirect:/";
            }

            QuestionDao questionDao = new QuestionDao();
            int questionId = questionDao.insert(question);

            Question questionCheck = questionDao.findById(questionId);
            if (questionCheck != null) {
                return "/qna/form";
            }
        }
        return "redirect:/";
    }

}
