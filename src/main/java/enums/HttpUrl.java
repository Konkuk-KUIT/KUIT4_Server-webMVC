package enums;

public enum HttpUrl {
    HOME("/"),
    LOGIN("/user/login"),
    LOGOUT("/user/logout"),
    LIST("/user/list"),
    UPDATE("/user/update"),
    UPDATE_FORM("/user/updateForm"),
    LOGIN_FAILED("/user/login_failed"),
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