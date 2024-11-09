package jwp.controller.user;

import core.db.MemoryUserRepository;
import core.mvc.Controller;
import jwp.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class UpdateUserFormController implements Controller {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        // 수정하고싶은 ID
        String userId = req.getParameter("userId");         // 수정되는 user
        User user = MemoryUserRepository.getInstance().findUserById(userId);

        // 현재 로그인된 ID
        HttpSession session = req.getSession();                    // 수정하는 user
        Object value = session.getAttribute("user");

        // user == null -> 요청한 userID가 db에 없을 때
        // value =- null -> 로그인 안한 상태에서 로그인 하려할 때
        if (user != null && value != null) {
            if (user.equals(value)) {            // 수정되는 user와 수정하는 user가 동일한 경우
                return "/user/updateForm.jsp";
            }
        }
        return "redirect:/";
    }
}