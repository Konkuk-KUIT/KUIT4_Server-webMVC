package core.mvc;

import jwp.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ForwardController implements Controller {

    private final String forwardUrl;

    public ForwardController(String forwardUrl) {
        this.forwardUrl = forwardUrl;
        if (forwardUrl == null) {
            throw new NullPointerException("forwardUrl is null");
        }
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            return "redirect:/login.jsp"; // 로그인 페이지로 리다이렉트
        }
        // 로그인된 사용자 ID를 폼에 삽입
        req.setAttribute("userId", ((User)session.getAttribute("user")).getUserId());
        return forwardUrl; // 로그인한 사용자에게는 forwardUrl로 포워드
    }
}
