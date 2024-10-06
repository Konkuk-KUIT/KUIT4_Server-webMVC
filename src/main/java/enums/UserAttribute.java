package enums;

public enum UserAttribute {
    USER_ID("userId"),
    PASSWORD("password"),
    NAME("name"),
    EMAIL("email");

    private final String key;

    UserAttribute(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public static String getValue(UserAttribute key) {
        return key.getKey();
    }
}