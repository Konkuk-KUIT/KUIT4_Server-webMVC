package core.mvc.controller;

import core.mvc.view.JsonView;
import core.mvc.view.JspView;
import core.mvc.view.ModelAndView;

// 하위호환성때문에 .. 기존의 Controller를 확장해서 구현 (라는 추측!?)
public interface AbstractController extends Controller {

    default ModelAndView jspView(String viewName) {
        return new ModelAndView(new JspView(viewName));
    };

    default ModelAndView jsonView(){
        return new ModelAndView(new JsonView());
    };
}
