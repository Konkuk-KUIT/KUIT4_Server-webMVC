package core.mvc;

import core.mvc.view.JsonView;
import core.mvc.view.JspView;
import core.mvc.view.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class AbstractController implements Controller {

    // 메서드를 직접 사용하는 것을 막기 위해 abstract class로 사용

    // JSP 뷰를 반환하는 메서드
    protected ModelAndView jspView(String viewName) {
        return new ModelAndView(new JspView(viewName));
    }

    // JSON 뷰를 반환하는 메서드
    protected ModelAndView jsonView() {
        return new ModelAndView(new JsonView());
    }

    @Override
    public abstract ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception;

}
