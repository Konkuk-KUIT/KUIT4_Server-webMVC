package controller;

import controller.Controller;
import jwp.model.Question;
import jwp.model.User;

import core.db.QuestionDAO;
import jwp.model.Question;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;


public class QuestionEditFormController implements Controller {

    private final QuestionDAO questionDao = new QuestionDAO();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            Long questionId = Long.parseLong(req.getParameter("questionId"));
            Question question = questionDao.findById(questionId);

            req.setAttribute("question", question);
            return "/qna/updateForm.jsp";
        }catch (SQLException e) {
            throw new ServletException("서블릿 오류", e);
        }
    }

}