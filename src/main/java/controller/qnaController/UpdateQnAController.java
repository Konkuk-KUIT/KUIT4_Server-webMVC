package controller.qnaController;

import controller.Controller;
import jwp.dao.QuestionDao;
import jwp.model.Question;
import jwp.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Objects;

public class UpdateQnAController implements Controller {
    private final QuestionDao questionDao=new QuestionDao();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        HttpSession session = req.getSession();
        Object value = session.getAttribute("user");
        //Todo: 코드가 안이뻐서 나중에 수정해야함
        if (value==null) {
            return "redirect:/user/login.jsp";
        }
        User user = (User) value;
        String questionId = req.getParameter("questionId");
        Question question =questionDao.findQuestionById(Integer.parseInt(questionId));
        if(Objects.equals(question.getWriter(), ((User) value).getUserId())){
            //같으면 수정폼으로 가게하고 아니면 오류 던지기
            return "redirect:/qna/updateForm.jsp";
        }
        throw new IllegalArgumentException("로그인된 유저와 질문 작성자가 다르면 질문을 수정할 수 없습니다.");

    }
}
