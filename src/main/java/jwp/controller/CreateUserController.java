package jwp.controller;

import core.db.MemoryUserRepository;
import jwp.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static jwp.constant.Path.LIST;
import static jwp.constant.Path.ROOT;
import static jwp.constant.UserQueryKey.*;

public class CreateUserController implements Controller {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String userId = req.getParameter(ID.getQueryKey());
        User findUser = MemoryUserRepository.getInstance().findUserById(userId);
        if (findUser == null) {
            User user = new User(req.getParameter(ID.getQueryKey()),
                    req.getParameter(PWD.getQueryKey()),
                    req.getParameter(NAME.getQueryKey()),
                    req.getParameter(EMAIL.getQueryKey()));
            MemoryUserRepository.getInstance().addUser(user);
            return LIST.getRedirectPath();
        }
        return ROOT.getRedirectPath();

    }
}
