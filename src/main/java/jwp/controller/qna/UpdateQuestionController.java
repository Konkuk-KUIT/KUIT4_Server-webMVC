package jwp.controller.qna;

import core.mvc.Controller;
import jwp.dao.QuestionDao;
import jwp.model.Question;
import jwp.model.User;
import jwp.util.UserSessionUtils;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.Map;

public class UpdateQuestionController implements Controller {

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

        User user = UserSessionUtils.getUserFromSession(session);
        String questionId = params.get("questionId");
        Question question = questionDao.findByQuestionId(Integer.parseInt(questionId));
        if (!question.isSameUser(user)) {
            throw new IllegalArgumentException("로그인된 유저와 질문 작성자가 다르면 질문을 수정할 수 없습니다.");
        }
        question.updateTitleAndContents(params.get("title"), params.get("contents"));
        questionDao.update(question);
        return "redirect:/";
    }

}
