package jwp.controller;

import core.mvc.Controller;
import jwp.dao.QuestionDao;
import jwp.model.Question;
import jwp.util.ControllerUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Date;
import java.time.LocalDate;

public class UpdateController implements Controller {

    private final QuestionDao questionDao = new QuestionDao();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        Question question = ControllerUtils.getQuestionAndCheckAuthorization(req, questionDao);

        String title = req.getParameter("title");
        String contents = req.getParameter("contents");

        question.setTitle(title);
        question.setContents(contents);
        question.setCreatedDate(Date.valueOf(LocalDate.now()));

        questionDao.update(question);
        return "redirect:/";
    }
}
