package jwp.controller.qna;

import core.mvc.controller.ControllerV2;
import jwp.dao.QuestionDao;
import jwp.model.Question;
import jwp.model.User;
import jwp.util.UserSessionUtils;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.Map;


public class UpdateQuestionFormControllerV2 implements ControllerV2 {

    private final QuestionDao questionDao = new QuestionDao();
    private HttpSession session;

    @Override
    public void setSession(HttpSession session) {
        this.session = session;
    }


    @Override
    public String execute(Map<String, String> params, Map<String, Object> model) throws SQLException {
        if (!UserSessionUtils.isLogined(session)) {
            return "redirect:/users/loginForm";
        }
        String questionId = params.get("questionId");
        Question question = questionDao.findByQuestionId(Integer.parseInt(questionId));
        User user = UserSessionUtils.getUserFromSession(session);
        if (!question.isSameUser(user)) {
            throw new IllegalArgumentException();
        }

        model.put("question", question);
        return "/qna/updateForm";
    }

}
