package jwp.controller;

import core.db.MemoryUserRepository;
import jwp.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static jwp.constant.Path.LIST;
import static jwp.constant.UserQueryKey.*;

public class UpdateUserController implements Controller {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        User user = new User(req.getParameter(ID.getQueryKey()),
                req.getParameter(PWD.getQueryKey()),
                req.getParameter(NAME.getQueryKey()),
                req.getParameter(EMAIL.getQueryKey()));
        MemoryUserRepository.getInstance().changeUserInfo(user);
        return LIST.getRedirectPath();
    }
}
