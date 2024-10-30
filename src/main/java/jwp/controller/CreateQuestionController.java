package jwp.controller;

import core.mvc.Controller;
import jwp.dao.QuestionDao;
import jwp.model.Question;
import jwp.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;

public class CreateQuestionController implements Controller {

    private final QuestionDao questionDao = new QuestionDao();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");

        if (user == null) {
            return "redirect:/user/loginForm";
        }

        String writer = req.getParameter("writer");
        String title = req.getParameter("title");
        String contents = req.getParameter("contents");
        Timestamp createdDate = new Timestamp(System.currentTimeMillis());

        System.out.println("Writer: " + writer);
        System.out.println("Title: " + title);
        System.out.println("Contents: " + contents);

        Question question = new Question(user.getUserId(), title, contents, createdDate, 0);
        questionDao.insert(question);

        return "redirect:/";
    }
}
