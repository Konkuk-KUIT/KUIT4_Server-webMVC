package constants;

public enum QueryKey {
    USER_ID("userID"),
    PASSWORD("password"),
    NAME("name"),
    EMAIL("email");

    private final String queryKey;

    QueryKey(String key) {
        this.queryKey = key;
    }

    public String getQueryKey() {
        return queryKey;
    }
}
