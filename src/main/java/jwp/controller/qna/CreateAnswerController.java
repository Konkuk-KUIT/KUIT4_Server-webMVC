package jwp.controller.qna;

import core.mvc.AbstractController;
import core.mvc.Controller;
import core.mvc.view.JsonView;
import core.mvc.view.ModelAndView;
import core.mvc.view.View;
import jwp.dao.AnswerDao;
import jwp.dao.QuestionDao;
import jwp.model.Answer;
import jwp.model.Question;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class CreateAnswerController implements AbstractController {

    private final AnswerDao answerDao = new AnswerDao();
    private final QuestionDao questionDao = new QuestionDao();

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

        Map<String, Object> model = new HashMap<>();
        model.put("answer", savedAnswer);

        /*
            이 컨트롤러에서는 JsonView를 활용해야했다. 따라서 jsonView() 메소드를 사용해주었다.
            이 때는 ModelAndView를 전달할 때, 해당 ModelAndView의 model을 통해 savedAnswer를 view로 넘겨주어야했다.
            그러다보니 ModelAndView 의 model에 데이터를 삽입해주는 동작이 필요했는데, 그 동작을 jsonView() 메소드를 통해 수행하고자 하다보니
            새로운 Map을 만든 후, 해당 Map에 attributeName, attributeValue를 저장하고 그 Map을 jsonView() 메소드의 인자로 전달해주었다.
            jsonView() 메소드에서는 인자로 넘어온 Map에서 데이터를 하나하나 꺼내어 ModelAndView 객체 내부의 model에 저장해주었다.
            이 ModelAndView 객체 내부의 model에 저장된 데이터들은 실제로 JsonView 의 render()에 의해서 JSON 데이터로 추후 변하게된다.
        */
        return jsonView(model);
    }

    @Override
    public ModelAndView jspView(String viewName) {
        return null;
    }

    @Override
    public ModelAndView jsonView(Map<String, Object> model) {
        ModelAndView mav = new ModelAndView(new JsonView());

        Iterator<String> keys = model.keySet().iterator();

        while (keys.hasNext()) {
            String key = keys.next();
            Object value = model.get(key);
            mav.addObject(key, value);
        }

        return mav;
    }
}
