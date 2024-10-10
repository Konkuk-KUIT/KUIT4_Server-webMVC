package controller.constant;

public enum SessionKey {
    USER_SESSION_KEY("user");
    String key;

    SessionKey(String key) {
        this.key = key;
    }
    public String getKey() {
        return key;
    }
}
