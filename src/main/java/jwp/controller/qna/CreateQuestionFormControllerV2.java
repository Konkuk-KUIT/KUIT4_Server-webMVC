package jwp.controller.qna;

import core.mvc.controller.ControllerV2;
import jwp.util.UserSessionUtils;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.Map;

public class CreateQuestionFormControllerV2 implements ControllerV2 {
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
