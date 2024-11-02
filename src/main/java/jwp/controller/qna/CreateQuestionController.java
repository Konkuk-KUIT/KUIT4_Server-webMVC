package jwp.controller.qna;

import core.mvc.JspController;
import core.mvc.view.JspView;
import core.mvc.view.ModelAndView;
import core.mvc.view.View;
import jwp.dao.QuestionDao;
import jwp.model.Question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateQuestionController implements JspController {

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
        return jspView("redirect:/");
    }

    @Override
    public ModelAndView jspView(String viewname) {
        final View view = new JspView(viewname);
        final ModelAndView modelAndView = new ModelAndView(view);
        return modelAndView;
    }
}
