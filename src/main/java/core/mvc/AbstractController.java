package core.mvc;

import core.mvc.view.JsonView;
import core.mvc.view.JspView;
import core.mvc.view.ModelAndView;
import core.mvc.view.View;

public abstract class AbstractController implements Controller {
    //todo:  interface 틀을 감싸는게(규약을 정하는) 목적
    //todo: abstract class 는 확장에 목적 따라ㅓ 여기선 abstract 가 나을듯이 맥락에서 고려해보기 -> 그냥 class는 구현을 할 위험이 있으니 abstract 처리 하는게 좋을ㄷㅅ
    //목적에 따라 다르다!!
    protected ModelAndView jspView(String viewName){
        return new ModelAndView(new JspView(viewName));
    }
    protected ModelAndView jsonView() {
        return new ModelAndView(new JsonView());
    }

}
