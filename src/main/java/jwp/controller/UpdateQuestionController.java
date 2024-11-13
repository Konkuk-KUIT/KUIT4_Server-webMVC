package jwp.controller;

import core.mvc.Controller;
import jwp.dao.QuestionDao;
import jwp.model.Question;
import jwp.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

public class UpdateQuestionController implements Controller {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse res) throws SQLException {
        HttpSession session = req.getSession();
        QuestionDao questionDao = new QuestionDao();

        int questionId = Integer.parseInt(req.getParameter("questionId"));
        req.setAttribute("questionId", questionId);

        User user = (User) session.getAttribute("user");
        String writer = questionDao.findById(questionId).getWriter();

        if (writer == user.getUserId()) {
            System.out.println("회원 일치");
            Question question = new Question(
                    req.getParameter("writer"),
                    req.getParameter("title"),
                    req.getParameter("contents")
            );
            if (question == null) { return "redirect:/qna/updateForm"; }

            questionDao.update(questionId, question);
            System.out.println("contents = " + question.getContents());

            Question questionCheck = questionDao.findById(questionId);
            if (questionCheck != null) {
                req.setAttribute("question", questionCheck);
                System.out.println("수정 완료");
                System.out.println("contents = " + questionCheck.getContents());
                return "redirect:/qna/show?questionId=" + questionId;
            }
            return "redirect:/qna/updateForm";
        }
        else throw new IllegalArgumentException();
    }
}