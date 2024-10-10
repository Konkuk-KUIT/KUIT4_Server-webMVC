package enums;

public enum Route {
    HOME("/"),
    SIGNUP("/user/signup"),
    LOGIN("/user/login"),
    LOGOUT("/user/logout"),
    USER_LIST("/user/userList"),
    UPDATE("/user/update"),
    UPDATE_FORM("/user/updateForm");

    private final String route;

    Route(String route) {
        this.route = route;
    }

    public String getRoute() {
        return route;
    }

    public String getRedirectCommand() {
        return "redirect:" + route;
    }
}
