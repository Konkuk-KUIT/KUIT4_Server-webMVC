package jwp.controller.qna;

import core.mvc.AbstractController;
import core.mvc.modelandview.ModelAndView;
import core.mvc.view.JspView;
import jwp.dao.AnswerDao;
import jwp.dao.QuestionDao;
import jwp.model.Answer;
import jwp.model.Question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowQnaController extends AbstractController {

    private final QuestionDao questionDao = new QuestionDao();
    private final AnswerDao answerDao = new AnswerDao();

    @Override
    public ModelAndView execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String questionId = req.getParameter("questionId");
        Question question = questionDao.findByQuestionId(Integer.parseInt(questionId));
        List<Answer> answers = answerDao.findAllByQuestionId(question.getQuestionId());

        return jspView().addObject("question", question)
                .addObject("answers", answers);
    }

    @Override
    protected ModelAndView jspView() {
        return new ModelAndView(new JspView("/qna/show.jsp"));
    }

    @Override
    protected ModelAndView jsonView() {
        return null;
    }
}
