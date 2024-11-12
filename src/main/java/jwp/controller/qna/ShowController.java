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

//        if(question != null){
//            HttpSession session = req.getSession();
//            User user = (User) session.getAttribute("user");
//            System.out.println("질문글 접근, questionId : " + questionId + ", userId : " + user.getUserId());
//        }

        req.setAttribute("question", question);

        List<Answer> answers = answerDao.findAllByQuestionId(questionId);
        req.setAttribute("answers", answers);

        return "/qna/show.jsp";
    }
}
