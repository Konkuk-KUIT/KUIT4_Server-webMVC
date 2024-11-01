package jwp.controller;

import core.mvc.Controller;
import jwp.dao.QuestionDao;
import jwp.model.Question;
import jwp.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ShowController implements Controller {

    private final QuestionDao questionDao = new QuestionDao();

//    @Override
//    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
//        int questionId = Integer.parseInt(req.getParameter("questionId"));
//        Question question = questionDao.findByQuestionId(questionId);
//        System.out.println(questionId);
//
//        req.setAttribute("question", question);     //question값을 못찾는것 같은데 왜지?
//
//        HttpSession session = req.getSession();
//        User user = (User)session.getAttribute("user");
//        System.out.println(user.getUserId());
//
//        return "/qna/show.jsp";
//    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String questionIdParam = req.getParameter("questionId");
        if (questionIdParam == null || questionIdParam.isEmpty()) {
            throw new IllegalArgumentException("questionId 파라미터가 없습니다.");
        }

        int questionId = Integer.parseInt(questionIdParam);
        Question question = questionDao.findByQuestionId(questionId);

        if (question == null) {
            throw new IllegalArgumentException("해당 questionId에 대한 질문이 존재하지 않습니다.");
        }

        req.setAttribute("question", question);
        return "/qna/show.jsp";
    }
}
