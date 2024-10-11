package Response.Constants;

public enum ResponseType {

    FORWARD(""),REDIRECT("redirect:");

    private String type;

    private ResponseType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
