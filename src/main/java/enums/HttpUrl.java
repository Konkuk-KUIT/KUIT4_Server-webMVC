package enums;

public enum HttpUrl {
    HOME("/"),
    LOGIN("/user/login"),
    LOGOUT("/user/logout"),
    UPDATE("/user/update"),
    UPDATE_FORM("/user/updateForm"),
    USER_LIST("/user/userList"),
    USER_SIGNUP("/user/signup");

    private final String value;

    HttpUrl(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}