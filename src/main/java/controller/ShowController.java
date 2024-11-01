package controller;

import core.db.QuestionDAO;
import jwp.model.Question;
import jwp.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ShowController implements Controller {

    private static final String USER_SESSION_KEY = "user";
    private final QuestionDAO questionDAO = new QuestionDAO();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Long questionId =  Long.parseLong(request.getParameter("questionId"));
            Question question = questionDAO.findById(questionId);
            request.setAttribute("question", question);

            // 세션에서 로그인 사용자 정보 가져오기
            HttpSession session = request.getSession();
            User loggedInUser = (User) session.getAttribute(USER_SESSION_KEY);

            // request에 로그인 사용자 정보 설정
            request.setAttribute("user", loggedInUser);

            return "/qna/show.jsp";
        } catch (SQLException e) {
            throw new ServletException("서블릿 오류", e);
        }
    }


}
