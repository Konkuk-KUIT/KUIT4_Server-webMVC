package jwp.controller;

import core.mvc.Controller;
import jwp.dao.QuestionDao;
import jwp.model.Question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditQuestionController implements Controller {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {


        QuestionDao questionDao = new QuestionDao();
        Question editingQuestion = questionDao.findById(req.getParameter("questionId"));
        editingQuestion.setTitle(req.getParameter("title"));
        editingQuestion.setContents(req.getParameter("contents"));

        questionDao.update(editingQuestion);

        return "redirect:/";
    }
}
