package jwp.controller;

import core.mvc.Controller;
import jwp.dao.QuestionDao;
import jwp.model.Question;
import jwp.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.time.LocalDate;

public class UpdateController implements Controller {

    private final QuestionDao questionDao = new QuestionDao();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        User user = (User) req.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/user/login";
        }

        int questionId = Integer.parseInt(req.getParameter("questionId"));
        Question question = questionDao.findByQuestionId(questionId);

        if (!user.getUserId().equals(question.getWriter())) {
            throw new IllegalArgumentException("수정 권한이 없습니다.");
        }

        String title = req.getParameter("title");
        String contents = req.getParameter("contents");

        question.setTitle(title);
        question.setContents(contents);
        question.setCreatedDate(Date.valueOf(LocalDate.now()));

        questionDao.update(question);
        return "redirect:/"; // 홈 화면으로 리디렉션
    }
}
