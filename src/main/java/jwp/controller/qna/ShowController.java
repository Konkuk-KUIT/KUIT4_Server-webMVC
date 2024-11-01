package jwp.controller.qna;

import core.mvc.Controller;
import jwp.dao.AnswerDao;
import jwp.dao.QuestionDao;
import jwp.model.Answer;
import jwp.model.Question;
import jwp.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ShowController implements Controller {

    private final QuestionDao questionDao = new QuestionDao();
    private final AnswerDao answerDao = new AnswerDao();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        int questionId = Integer.parseInt(req.getParameter("questionId"));
        Question question = questionDao.findByQuestionId(questionId);

        if(question != null){
            System.out.println(questionId);
        }

        req.setAttribute("question", question);     //question값을 못찾는것 같은데 왜지?

        List<Answer> answers = answerDao.findAllByQuestionId(questionId);  //파라미터로 'question.getQuestionId().intValue()' 를 넣으면 왜 null 이지?
        req.setAttribute("answers", answers);

        return "/qna/show.jsp";
    }
}
