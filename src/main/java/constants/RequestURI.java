package constants;

import static constants.RedirectPrefix.REDIRECT;

public enum RequestURI {
    SIGNUP("/user/signup"),
    ROOT("/"),
    USER_LIST("/user/userList"),
    USER_LIST_JSP("/user/list.jsp"),
    HOME_JSP("/home.jsp"),
    LOGIN("/user/login"),
    LOGIN_JSP("/user/login.jsp"),
    LOGIN_FAILED_JSP("/user/login_failed.jsp"),
    LOGOUT("/user/logout"),
    UPDATE("/user/update"),
    UPDATE_FORM("/user/updateForm"),
    UPDATE_FORM_JSP("/user/updateForm.jsp");

    private final String uri;

    RequestURI(String uri) {
        this.uri = uri;
    }

    public String getUri() {
        return uri;
    }

    public String getRedirectUri() {
        return REDIRECT.getRedirectPrefix() + uri;
    }
}
