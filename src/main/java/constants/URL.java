package constants;

public enum URL {
    LIST_JSP("/user/list.jsp"),
    USER_LIST("/user/userList"),
    LOGIN_JSP("/user/login.jsp"),
    LOGIN("/user/login"),
    LOGIN_FAILED_JSP("/user/login_failed.jsp"),
    LOGOUT("/user/logout"),
    SIGNUP("/user/signup"),
    FORM_JSP("/user/form.jsp"),
    UPDATE("/user/update"),
    UPDATE_FORM("/user/updateForm"),
    UPDATE_FORM_JSP("/user/updateForm.jsp"),
    ROOT("/"),
    HOME_JSP("/home.jsp");

    private final String url;

    URL(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
