package controller;

import core.db.MemoryUserRepository;
import jwp.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static constants.URL.UPDATE_FORM_JSP;
import static constants.URL.USER_LIST;

public class UpdateUserFormController implements Controller {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //로그인 상태에만 해당 url에 접근할 수 있음
        String userId = req.getParameter("userId");
        User accessUser = MemoryUserRepository.getInstance().findUserById(userId);

        HttpSession session = req.getSession();
        User loginedUser = (User) session.getAttribute("user");            //로그인 정보 세션

        if(accessUser.isSameUser(loginedUser)){
            req.setAttribute("user", accessUser);

            return UPDATE_FORM_JSP.getUrl();                            // forward
        }

        //다른 User의 정보 수정에 접근 한 경우(권한 없음)
        return "redirect:"+USER_LIST.getUrl();                          // redirect
    }
}