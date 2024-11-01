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

        System.out.println(user.getUserId());
        System.out.println(questionId);

        Question question = questionDao.findByQuestionId(questionId);
        if(question != null && !Objects.equals(question.getWriter(), user.getUserId())) {
            throw new IllegalArgumentException();
        }

        req.setAttribute("question", question);     //안넘어 간다는 건가? 자꾸 jsp 파일에서 qestionID를 못읽는데?
        return "/qna/update.jsp";
    }
}
