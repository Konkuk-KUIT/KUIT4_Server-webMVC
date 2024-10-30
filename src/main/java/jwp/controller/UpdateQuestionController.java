package jwp.controller;

import core.mvc.Controller;
import jwp.dao.QuestionDao;
import jwp.model.Question;
import jwp.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

public class UpdateQuestionController implements Controller {

    private final QuestionDao questionDao = new QuestionDao();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        HttpSession session = req.getSession();
        User loggedInUser = (User) session.getAttribute("user");

        int questionId = Integer.parseInt(req.getParameter("questionId"));
        String title = req.getParameter("title");
        String contents = req.getParameter("contents");

        Question question = questionDao.findByQuestionId(questionId);

        if (loggedInUser == null || !loggedInUser.getUserId().equals(question.getWriter())) {
            throw new IllegalArgumentException("수정 권한이 없습니다.");
        }

        Question updatedQuestion = new Question(
                question.getQuestionId(),
                loggedInUser.getUserId(),
                title,
                contents,
                LocalDateTime.now(),
                question.getCountOfAnswer()
        );

        questionDao.update(updatedQuestion);

        return "redirect:/";
    }
}
