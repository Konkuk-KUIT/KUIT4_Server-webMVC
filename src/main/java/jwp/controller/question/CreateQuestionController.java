package jwp.controller.question;

import core.db.DBQuestionRepository;
import core.mvc.Controller;
import jwp.model.Question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateQuestionController implements Controller {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Question question = new Question(req.getParameter("writer"),
                req.getParameter("title"),
                req.getParameter("contents"));

        // addQuestion에서는 Question 객체의 writer, title, contents 만 사용하니까
        DBQuestionRepository.getInstance().addQuestion(question);
        System.out.println("question 추가 완료");
        return "redirect:/";
    }
}