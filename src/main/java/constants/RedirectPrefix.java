package constants;

public enum RedirectPrefix {
    REDIRECT("redirect:");

    private final String redirectPrefix;

    RedirectPrefix(String prefix) {
        this.redirectPrefix = prefix;
    }

    public String getRedirectPrefix() {
        return redirectPrefix;
    }
}
