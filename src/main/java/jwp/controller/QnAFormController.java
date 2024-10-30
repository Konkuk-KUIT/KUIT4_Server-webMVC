package jwp.controller;

import core.db.MemoryUserRepository;
import core.mvc.Controller;
import jwp.util.UserSessionUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class QnAFormController implements Controller {

    private final String forwardUrl;

    public QnAFormController(String forwardUrl) {
        this.forwardUrl = forwardUrl;
        if (forwardUrl == null) {
            throw new NullPointerException("forwardUrl is null");
        }
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        HttpSession session = req.getSession();
        if (UserSessionUtils.isLogined(session)) {
            return forwardUrl;
        } else {
            HomeController homeController = new HomeController();
            return homeController.execute(req, resp);
        }
    }
}