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

public class QuestionEditController implements Controller {

    private final QuestionDAO questionDAO = new QuestionDAO();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Long questionId = Long.valueOf(request.getParameter("questionId"));
            Question question = questionDAO.findById(questionId);

            // 로그인한 사용자 정보
            User loggedInUser = (User) request.getSession().getAttribute("user");

            // 질문 작성자와 로그인한 사용자가 같은지 확인
            if (!question.getWriter().equals(loggedInUser.getUserId())) {
                throw new IllegalArgumentException("수정 권한이 없습니다.");
            }

            // 수정된 질문 내용 가져오기
            String title = request.getParameter("title");
            String contents = request.getParameter("contents");

            // 수정 요청을 처리
            question.setTitle(title);
            question.setContents(contents);
            questionDAO.update(question); // 수정 메서드 호출

            return "redirect:/"; // 수정 완료 후 home으로 리다이렉트

        } catch (SQLException e) {
            throw new ServletException("서블릿 오류", e);
        }
    }
}
