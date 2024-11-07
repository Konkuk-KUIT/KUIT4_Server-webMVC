package core.mvc;

import core.mvc.view.JsonView;
import core.mvc.view.JspView;
import core.mvc.view.View;


public class ViewResolver {
    private static final String REDIRECT_PREFIX = "redirect:";
    public static final String JSON_VIEW_PREFIX = "jsonView";

    public View getView(String viewName) {

        if (viewName.equals(JSON_VIEW_PREFIX)) {
            return new JsonView();
        }
        return jspViewResolver(viewName);
    }

    private JspView jspViewResolver(String viewName) {
        if (viewName.startsWith(REDIRECT_PREFIX)) {
            return new JspView(viewName.substring(REDIRECT_PREFIX.length()));
        }
        return new JspView(viewName + ".jsp");
    }


}
