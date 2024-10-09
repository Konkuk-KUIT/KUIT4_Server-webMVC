package jwp.constant;

public enum UserQueryKey {
    ID("userId"),
    PWD("password"),
    NAME("name"),
    EMAIL("email"),
    ;

    private final String queryKey;

    UserQueryKey(String queryKey) {
        this.queryKey = queryKey;
    }

    public String getQueryKey() {
        return queryKey;
    }
}
