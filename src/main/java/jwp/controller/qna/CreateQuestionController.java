package jwp.controller.qna;

import core.mvc.AbstractController;
import core.mvc.modelandview.ModelAndView;
import core.mvc.view.JspView;
import jwp.dao.QuestionDao;
import jwp.model.Question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateQuestionController extends AbstractController {

    private final QuestionDao questionDao = new QuestionDao();

    @Override
    public ModelAndView execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Question question = new Question(
                req.getParameter("writer"),
                req.getParameter("title"),
                req.getParameter("contents"),
                0);
        Question savedQuestion = questionDao.insert(question);
        System.out.println("saved question id= " + savedQuestion.getQuestionId());


        return jspView();
    }

    @Override
    protected ModelAndView jspView() {
        return new ModelAndView(new JspView("redirect:/"));
    }

    @Override
    protected ModelAndView jsonView() {
        return null;
    }
}
