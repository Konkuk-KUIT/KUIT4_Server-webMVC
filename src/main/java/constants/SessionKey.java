package constants;

public enum SessionKey {
    SESSION_KEY("user");

    private final String sessionKey;

    SessionKey(String key) {
        this.sessionKey = key;
    }

    public String getSessionKey() {
        return sessionKey;
    }
}
