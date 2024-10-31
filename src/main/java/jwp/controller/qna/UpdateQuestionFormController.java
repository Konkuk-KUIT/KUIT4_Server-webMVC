package jwp.controller.qna;

import core.db.question.DBQuestionRepository;
import core.db.user.DBUserRepository;
import core.mvc.Controller;
import jwp.model.Question;
import jwp.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UpdateQuestionFormController implements Controller {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        String rawQuestionId = req.getParameter("questionId");
        if(rawQuestionId == null) return "redirect:/";
        Long questionId = Long.valueOf(rawQuestionId);

        String writer = req.getParameter("writer"); // create 시에 writer를 sessionScope.user.userId로 설정해서
        User user = DBUserRepository.getInstance().findUserById(writer);

        HttpSession session = req.getSession();
        Object value = session.getAttribute("user");

        if (user != null && value != null) {
            if (user.equals(value)) {
                Question foundQuestion = DBQuestionRepository.getInstance().findQuestionById(questionId);
                req.setAttribute("question", foundQuestion);
                return "/qna/updateForm.jsp";
            }
        }

        return "redirect:/";
    }
}