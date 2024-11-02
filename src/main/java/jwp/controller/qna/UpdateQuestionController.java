package jwp.controller.qna;

import core.mvc.Controller;
import core.mvc.view.JspView;
import core.mvc.view.View;
import jwp.dao.QuestionDao;
import jwp.model.Question;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateQuestionController implements Controller {
    private final QuestionDao questionDao = new QuestionDao();

    @Override
    public View execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        String questionId = req.getParameter("questionId");
        String title = req.getParameter("title");
        String contents = req.getParameter("contents");

        Question question = questionDao.findByQuestionId(Integer.parseInt(questionId));
        question.setTitle(title);
        question.setContents(contents);
        questionDao.update(question);

        return new JspView("redirect:/");
    }

}
