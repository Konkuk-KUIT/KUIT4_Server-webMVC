package jwp.controller;

import core.mvc.Controller;
import jwp.dao.QuestionDao;
import jwp.model.Question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowController implements Controller {

    private final QuestionDao questionDao = new QuestionDao();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String questionIdParam = req.getParameter("questionId");
        if (questionIdParam == null || questionIdParam.isEmpty()) {
            return "redirect:/";
        }

        int questionId = Integer.parseInt(questionIdParam);
        Question question = questionDao.findByQuestionId(questionId);

        if (question == null) {
            return "redirect:/";
        }

        req.setAttribute("question", question);
        return "/qna/show.jsp";
    }
}
