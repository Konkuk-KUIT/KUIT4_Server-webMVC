package jwp.controller;

import core.mvc.Controller;
import jwp.dao.QuestionDao;
import jwp.model.Question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowQuestionController implements Controller {

    private final QuestionDao questionDao = new QuestionDao();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String questionIdParam = req.getParameter("questionId");

        // questionId가 전달되지 않으면 홈으로 이동
        if (questionIdParam == null || questionIdParam.isEmpty()) {
            return "redirect:/";
        }

        long questionId = Long.parseLong(questionIdParam);
        Question question = questionDao.findByQuestionId(questionId);

        req.setAttribute("question", question);

        return "/qna/show.jsp";
    }
}
