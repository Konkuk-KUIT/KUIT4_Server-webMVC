package Controller.user;

import Controller.Controller;
import jwp.dao.QuestionDao;
import jwp.model.Question;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class HomeController implements Controller {

    private final QuestionDao questionDao = new QuestionDao();

    @Override
    public String handleRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException {

        List<Question> questions = questionDao.findAllQuestions();
        req.setAttribute("questions", questions);
        return "/home.jsp";

    }
}
