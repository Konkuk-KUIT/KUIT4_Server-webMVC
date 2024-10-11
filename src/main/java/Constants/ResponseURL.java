package Constants;

public enum ResponseURL {
    HOME("/"),
    USER_SIGNUP("/user/signup"),
    USER_LIST("/user/list"),
    USER_LOGIN("/user/login"),
    USER_LOGOUT("/user/logout"),
    USER_UPDATE("/user/update"),
    USER_UPDATE_FORM("/user/updateForm/");

    private String path;

    ResponseURL(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
