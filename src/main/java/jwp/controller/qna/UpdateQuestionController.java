package jwp.controller.qna;

import core.db.question.DBQuestionRepository;
import core.mvc.Controller;
import jwp.model.Question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UpdateQuestionController implements Controller {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Long questionId = Long.valueOf(req.getParameter("questionId"));
        Question foundQuestion = DBQuestionRepository.getInstance().findQuestionById(questionId);
        if(foundQuestion == null) {
            System.out.println("questionId에 해당하는 question이 없습니다.");
            return "redirect:/";
        }
        Question question = new Question(foundQuestion.getQuestionId(),
                req.getParameter("writer"),
                req.getParameter("title"),
                req.getParameter("contents"),
                foundQuestion.getCreatedDate(),
                foundQuestion.getCountOfAnswer());

        DBQuestionRepository.getInstance().update(question);
        System.out.println("question 수정 완료");
        return "redirect:/";
    }
}