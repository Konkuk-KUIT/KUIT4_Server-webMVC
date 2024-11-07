package jwp.controller.qna;

import core.mvc.Controller;
import jwp.util.UserSessionUtils;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.Map;

public class CreateQuestionFormController implements Controller {
    private HttpSession session;

    @Override
    public void setSession(HttpSession session) {
        this.session = session;
    }

    @Override
    public String execute(Map<String, String> params, Map<String, Object> model) throws SQLException {
        if (UserSessionUtils.isLogined(session)) {
            return "/qna/form";
        }
        return "redirect:/user/loginForm";
    }
}
