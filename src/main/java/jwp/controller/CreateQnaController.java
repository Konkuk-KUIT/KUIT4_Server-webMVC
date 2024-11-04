package jwp.controller;

import core.mvc.Controller;
import jwp.dao.QuestionDao;
import jwp.model.Question;
import jwp.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.time.LocalDate;

public class CreateQnaController implements Controller {

    private final QuestionDao questionDao = new QuestionDao();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        User user = (User) req.getSession().getAttribute("user");
        if (user == null) {
            return "redirect:/user/login";
        }

        String writer = user.getUserId();

        String title = req.getParameter("title");
        String contents = req.getParameter("contents");

        Question question = new Question(0, writer, title, contents, Date.valueOf(LocalDate.now()), 0);
        questionDao.insert(question);

        return "redirect:/";
    }
}
