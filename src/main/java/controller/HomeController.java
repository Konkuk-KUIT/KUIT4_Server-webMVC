package controller;

import core.db.QuestionDAO;
import jwp.model.Question;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class HomeController implements Controller {

    private final QuestionDAO questionDAO = new QuestionDAO();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Question> questions = questionDAO.findAll();
            request.setAttribute("questions", questions);
            return "/home.jsp";
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }
}