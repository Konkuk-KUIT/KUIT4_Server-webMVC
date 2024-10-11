public enum URLs {
    HOME("/"),
    SIGNUP("/user/signup"),
    LOGIN("/user/login"),
    LOGOUT("/user/logout"),
    LIST("/user/userList"),
    UPDATE("/user/updateForm");

    String url;

    URLs(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
