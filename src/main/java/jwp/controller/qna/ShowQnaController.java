package jwp.controller.qna;

import core.mvc.AbstractController;
import core.mvc.view.JspView;
import core.mvc.view.ModelAndView;
import jwp.dao.AnswerDao;
import jwp.dao.QuestionDao;
import jwp.model.Answer;
import jwp.model.Question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowQnaController implements AbstractController {

    private final QuestionDao questionDao = new QuestionDao();
    private final AnswerDao answerDao = new AnswerDao();

    @Override
    public ModelAndView execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String questionId = req.getParameter("questionId");
        Question question = questionDao.findByQuestionId(Integer.parseInt(questionId));
        List<Answer> answers = answerDao.findAllByQuestionId(question.getQuestionId());
//        req.setAttribute("question", question);
//        req.setAttribute("answers", answers);

        System.out.println("question id : " + question.getQuestionId());


        return jspView("/qna/show.jsp")
                .addObject("question", question)
                .addObject("answers", answers);
    }

    @Override
    public ModelAndView jspView(String url) {
        return new ModelAndView(new JspView(url));
    }

    @Override
    public ModelAndView jsonView( ) {
        return null;
    }
}
