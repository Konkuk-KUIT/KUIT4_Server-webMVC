package controller.qnaController;

import controller.Controller;
import jwp.dao.QuestionDao;
import jwp.model.Question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

public class UpdateQnAFormController implements Controller {
    private final QuestionDao questionDao=new QuestionDao();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        LocalDateTime createdDate = LocalDateTime.now();

      //todo: 시간, 컨텐츠 , 등등 ㄴ다시 넣기


        return null;
    }
}
