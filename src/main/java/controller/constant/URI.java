package controller.constant;

public enum URI {
    ROOT("/"),
    HOME("/home"),
    LOGIN("/user/login"),
    LOGOUT("/user/logout"),
    LOGIN_FAILED("/user/login_failed"),
    SIGNUP("/user/signup"),
    UPDATE_FORM("/user/updateForm"),
    UPDATE("/user/update"),
    USER_LIST("/user/userList")
    ;

    private final String uri;

    URI(String uri) {
        this.uri = uri;
    }

    public String getRedirectURI() {
        return "redirect:" + uri;
    }

    public String getJSPPath() {
        return uri + ".jsp";
    }

    public String getURI() {
        return uri;
    }
}
