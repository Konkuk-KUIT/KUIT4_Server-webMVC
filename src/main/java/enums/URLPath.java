package enums;


public enum URLPath {
    INDEX("/home.jsp"),
    LOGIN("/user/login"),
    SIGNUP("/user/signup"),
    LIST("/user/list.jsp"),
    LOGINFAIL("/user/login_failed.jsp");


    private final String path;

    URLPath(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    @Override
    public String toString() {
        return path;
    }

}
