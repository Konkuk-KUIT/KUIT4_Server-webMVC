package controller.qnaController;

import controller.Controller;
import jwp.dao.QuestionDao;
import jwp.model.Question;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class CreateQnAController implements Controller {
    private final QuestionDao questionDao=new QuestionDao();
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        LocalDateTime createdDate = LocalDateTime.now();

        Question question = new Question(
                req.getParameter("writer"),
                req.getParameter("title"),
                req.getParameter("contents"),
                createdDate,
                0);
        //id는 자동 생성 해주니까
        Question savedQuestion = questionDao.insert(question);
        System.out.println("saved question id= " + savedQuestion.getQuestionId());
        return "redirect:/";

    }
}
