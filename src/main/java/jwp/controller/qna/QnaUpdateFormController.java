package jwp.controller.qna;

import core.mvc.Controller;
import jwp.dao.QuestionDao;
import jwp.model.Question;
import jwp.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;

public class QnaUpdateFormController implements Controller {

    private final QuestionDao questionDao = new QuestionDao();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        HttpSession session = req.getSession();
        User user = (User)session.getAttribute("user");
        int questionId = Integer.parseInt(req.getParameter("questionId"));

//        System.out.println("질문글 수정 접근");
//        System.out.println("userId : " + user.getUserId() + ", questionId : " + questionId);

        Question question = questionDao.findByQuestionId(questionId);
        if(question != null && !Objects.equals(question.getWriter(), user.getUserId())) {
            throw new IllegalArgumentException();
        }

        req.setAttribute("question", question);
        return "/qna/update.jsp";
    }
}
