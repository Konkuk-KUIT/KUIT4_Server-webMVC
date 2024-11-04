package jwp.util;

import jwp.dao.QuestionDao;
import jwp.model.Question;
import jwp.model.User;

import javax.servlet.http.HttpServletRequest;

public class ControllerUtils {

    public static Question getQuestionAndCheckAuthorization(HttpServletRequest req, QuestionDao questionDao) throws Exception {

        User user = (User) req.getSession().getAttribute("user");
        if (user == null) {
            throw new IllegalStateException("로그인이 필요합니다.");
        }

        int questionId = Integer.parseInt(req.getParameter("questionId"));
        Question question = questionDao.findByQuestionId(questionId);

        if (!user.getUserId().equals(question.getWriter())) {
            throw new IllegalArgumentException("수정 권한이 없습니다.");
        }

        return question;
    }
}
