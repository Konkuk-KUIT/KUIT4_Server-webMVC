package core.mvc;

import core.mvc.view.ModelAndView;
import core.mvc.view.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Controller {
    //반환값 View에서 MAV로 변경
    ModelAndView execute(HttpServletRequest req, HttpServletResponse resp) throws Exception;
}
