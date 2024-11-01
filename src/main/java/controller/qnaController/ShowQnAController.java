package controller.qnaController;

import controller.Controller;
import jwp.dao.QuestionDao;
import jwp.model.Question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowQnAController implements Controller {
    private final QuestionDao questionDao=new QuestionDao();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //req로 query 문에서 추출 해서 해야
        String questionId = req.getParameter("questionId");
        Question question =questionDao.findQuestionById(Integer.parseInt(questionId));


        req.setAttribute("question",question);
        return "/qna/show.jsp";
    }
}
