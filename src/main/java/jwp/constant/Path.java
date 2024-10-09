package jwp.constant;

public enum Path {
    ROOT("/"),
    SIGNUP("/user/signup"),
    LOGIN("/user/login"),
    LIST("/user/userList"),
    LOGOUT("/user/logout"),
    UPDATE("/user/update"),
    UPDATEFORM("/user/updateForm"),
    USER_LOGIN("/user/loginuser"),
    USER_CREATE("/user/createuser"),
    LOGIN_FORWARD("/user/login.jsp"),
    FORM_FORWARD("/user/form.jsp"),
    HOME("/home.jsp"),
    LIST_FORWARD("/user/list.jsp"),
    LOGIN_FAILED("/user/login_failed.jsp"),
    UPDATE_FORWARD("/user/updateForm.jsp");


    private final String path;

    Path(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public String getRedirectPath() {
        return "redirect:" + path;
    }
}
