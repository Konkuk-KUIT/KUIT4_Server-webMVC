package jwp.controller.qna;

import core.mvc.AbstractController;
import core.mvc.view.JsonView;
import core.mvc.view.ModelAndView;
import jwp.dao.AnswerDao;
import jwp.dao.QuestionDao;
import jwp.model.Answer;
import jwp.model.Question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CreateAnswerController implements AbstractController {

    private final AnswerDao answerDao = new AnswerDao();
    private final QuestionDao questionDao = new QuestionDao();
    //Dao도 굳이 따지면 model 이라고 볼 수도 있음
    //view와 controller을 제외한 것을 model이라 보는 관점도 있음
    //service 클래스를 만들어서 해당 클래스에서 Dao를 관리
    //  >> AnswerDao, QuestionDao 을 사용하는 것이 아니라 service 객체를 만들어서 해당 객체를 호출하는 식으로 model 사용의 모호함을 지울 수 있음

    @Override
    public ModelAndView execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        System.out.println("[CreateAnswerController] 실행");
        int questionId = Integer.parseInt(req.getParameter("questionId"));
        Answer answer = new Answer(questionId,
                req.getParameter("writer"),
                req.getParameter("contents"));

        Answer savedAnswer = answerDao.insert(answer);              

        Question question = questionDao.findByQuestionId(questionId);
        question.increaseCountOfAnswer();
        questionDao.updateCountOfAnswer(question);

//        req.setAttribute("answer", savedAnswer);
        return jsonView().addObject("answer", savedAnswer);
    }

    @Override
    public ModelAndView jsonView() {
        return new ModelAndView(new JsonView());
    }

    @Override
    public ModelAndView jspView(String url) {
        return null;
    }
}
