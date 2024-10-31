package controller;

import jwp.dao.QuestionDao;
import jwp.model.Question;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class HomeController  implements Controller {
    QuestionDao questionDao =new QuestionDao();
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        List<Question> questions= null;
        try {
            questions = questionDao.readAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        System.out.println(questions.size());
        req.setAttribute("questions",questions);
        return "/home.jsp";
    }
}
