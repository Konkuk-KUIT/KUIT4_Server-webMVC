package jwp.controller.qna;

import core.mvc.AbstractController;
import core.mvc.view.JspView;
import core.mvc.view.ModelAndView;
import jwp.dao.QuestionDao;
import jwp.model.Question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateQuestionController implements AbstractController {

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

        // ModelAndView 객체를 반환하도록 수정
        return jspView("redirect:/");
    }

    @Override
    public ModelAndView jspView(String viewName) {
        return new ModelAndView(new JspView(viewName));
    }

    @Override
    public ModelAndView jsonView() {
        return null;
    }
}
