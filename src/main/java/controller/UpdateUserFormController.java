package controller;

import constants.QueryKey;
import constants.RequestURL;
import core.db.MemoryUserRepository;
import core.db.Repository;
import jwp.model.User;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import static constants.QueryKey.*;
import static constants.RequestURL.*;

public class UpdateUserFormController extends HttpServlet implements Controller{

    Repository repository = MemoryUserRepository.getInstance();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String userId = req.getParameter(ID.getKey());
        User findUser = repository.findUserById(userId);

        HttpSession session = req.getSession();
        Object value = session.getAttribute("user");

        // 로그인 하지 않은 상황에서는 해당 URL로 요청이 올 수 없기 때문에 검증 로직은 삭제
        User currentUser = (User) value;

        if(findUser.isSameUser(currentUser)){
            req.setAttribute("user", findUser);

            // 현재 로그인 된 유저와 수정하기 버튼을 누른 유저가 동일한 유저라면 updateForm.jsp 로 forward 할 수 있도록 url 반환
            return UPDATE_FORM_JSP.getUrl();
        }

        // 현재 로그인 된 유저와 수정하기 버튼을 누른 유저가 서로 다른 유저라면 다시 list로 redirect할 수 있도록 url 반환
        return "redirect:" + USER_LIST.getUrl();
    }
}
