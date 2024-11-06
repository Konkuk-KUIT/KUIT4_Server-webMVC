package core.mvc;

import core.mvc.view.ModelAndView;

import java.util.Map;

public interface AbstractController extends Controller{

    // JspView의 경우 viewName에 따라서 어떤 경로로 forward할지, redirect를 할 지 결정한다. 따라서 인자로 viewName을 받을 수 있도록 해주었다.
    ModelAndView jspView(String viewName);


    // JsonView의 경우 model을 전달받아서 해당 모델의 정보를 모두 json 형식으로 바꿔주는 형태로 동작을 한다.
    // 그러다보니 ModelAndView 객체를 생성한 후, 해당 객체 내의 model에 데이터를 삽입해주기 위해서는 ModelAndView에 삽입해 줄 데이터를 지니고 있는
    // 원본 자료구조가 필요했다. 따라서 인자로 해당 데이터를 가지고 있는 Map을 전달해줄 수 있도록 해주었다.
    ModelAndView jsonView(Map<String, Object> model);

}
