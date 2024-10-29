package jwp.controller.qna;

import core.mvc.Controller;
import jwp.dao.QuestionDao;
import jwp.model.Question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class QnaUpdateController implements Controller {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        QuestionDao questionDao = new QuestionDao();
        String title = req.getParameter("title");
        String contents = req.getParameter("contents");
        String questionId = req.getParameter("questionId");
        Question question = questionDao.findByQuestionId(Integer.parseInt(questionId));
        question.setTitle(title);
        question.setContents(contents);
        questionDao.update(question);
        return "redirect:/";
    }
}
