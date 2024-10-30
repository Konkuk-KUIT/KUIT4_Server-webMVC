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
        QuestionDao questionDao = new QuestionDao();

        if (session != null) {
            Question question = new Question(
                    req.getParameter("writer"),
                    req.getParameter("title"),
                    req.getParameter("contents")
            );
            if (question == null) { return "redirect:/qna/form"; }

            int questionId = questionDao.insert(question);

            Question questionCheck = questionDao.findById(questionId);
            if (questionCheck != null) {
                return "/qna/show";
            }

            return "redirect:/qna/form";
        }
        return "redirect:/";
    }

}
