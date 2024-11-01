package controller;

import core.db.QuestionDAO;
import jwp.model.Question;
import jwp.model.QuestionRequest;
import jwp.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

public class QuestionCreateController implements Controller {

    private final QuestionDAO questionDAO = new QuestionDAO();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String writer = request.getParameter("writer");
            String title = request.getParameter("title");
            String contents = request.getParameter("contents");

            // Question 객체 생성
            QuestionRequest newQuestion = new QuestionRequest(
                    writer,
                    title,
                    contents,
                    0  // 답변 수 초기값 0
            );

            questionDAO.insert(newQuestion);
            return "redirect:/";

        } catch (SQLException e) {
            throw new ServletException("서블릿 오류", e);
        }
    }




}
