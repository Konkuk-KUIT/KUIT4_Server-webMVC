package constants;

public enum AttributeKey {
    ATTRIBUTE_KEY("users");

    private final String attributeKey;

    AttributeKey(String key) {
        this.attributeKey = key;
    }

    public String getAttributeKey() {
        return attributeKey;
    }
}
