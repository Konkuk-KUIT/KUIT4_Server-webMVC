package enums;

public enum HttpUrl {
    INDEX_JSP("/index.jsp"),
    REGISTER_FORM_HTML("/user/form.jsp"),
    LOGIN_HTML("/user/login.jsp"),
    LIST_HTML("/user/list.jsp"),
    LOGIN_FAILD("/user/login_failed.jsp"),
    USER_LIST("/user/userlist"),
    USER_SIGNUP("/user/signup");

    private final String value;

    HttpUrl(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}