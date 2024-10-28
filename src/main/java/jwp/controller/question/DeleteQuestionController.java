package jwp.controller.question;

import core.db.DBQuestionRepository;
import core.db.DBUserRepository;
import core.mvc.Controller;
import jwp.model.Question;
import jwp.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DeleteQuestionController implements Controller {

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
                DBQuestionRepository.getInstance().delete(questionId);
                System.out.println("question 삭제 완료");
                return "redirect:/";
            }
        }

        System.out.println("question 삭제 실패");
        return "redirect:/";
    }
}
