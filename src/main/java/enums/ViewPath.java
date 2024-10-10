package enums;

public enum ViewPath {
    HOME_JSP("/home.jsp"),
    LOGIN_JSP("/user/login.jsp"),
    LOGIN_FAILED_JSP("/user/login_failed.jsp"),
    LIST_JSP("/user/list.jsp"),
    FORM_JSP("/user/form.jsp");

    private final String path;

    ViewPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
