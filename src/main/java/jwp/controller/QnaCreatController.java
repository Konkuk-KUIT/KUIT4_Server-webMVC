package jwp.controller;

import core.mvc.Controller;
import jwp.dao.QuestionDao;
import jwp.model.Question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;

public class QnaCreatController implements Controller {

    private final QuestionDao questionDao = new QuestionDao();
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String writer = req.getParameter("writer");
        String title = req.getParameter("title");
        String contents = req.getParameter("contents");
        Timestamp timeStamp = new Timestamp(System.currentTimeMillis());
        int countOfAnswer = 0;

        Question question = new Question(writer, title, contents, timeStamp, countOfAnswer);

        Question savedQuestion = questionDao.insert(question);
        System.out.println("saved question id= " + savedQuestion.getQuestionId());      //정상 insert 확인

        return "redirect:/";
    }
}
