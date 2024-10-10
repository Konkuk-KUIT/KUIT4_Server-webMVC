package constants;

public enum QueryKey {
    USER_ID("userId"),
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
