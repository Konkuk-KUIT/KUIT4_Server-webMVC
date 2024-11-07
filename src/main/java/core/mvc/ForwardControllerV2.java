package core.mvc;

import core.mvc.controller.ControllerV2;

import java.util.Map;

public class ForwardControllerV2 implements ControllerV2 {

    private final String forwardUrl;

    public ForwardControllerV2(String forwardUrl) {
        this.forwardUrl = forwardUrl;
        if (forwardUrl == null) {
            throw new NullPointerException("forwardUrl is null");
        }
    }

    @Override
    public String execute(Map<String, String> params, Map<String, Object> model) {
        return forwardUrl;
    }
}
