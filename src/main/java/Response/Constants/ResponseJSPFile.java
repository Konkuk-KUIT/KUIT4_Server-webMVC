package Response.Constants;

public enum ResponseJSPFile implements ResponsePath{

    HOME("/home.jsp"),USER_LIST("/user/list.jsp"),LOGIN_FAILED("/user/login_failed.jsp"),USER_UPDATEFORM("/user/updateForm.jsp");

    private String filename;

    ResponseJSPFile(String filename) {
        this.filename = filename;
    }

    @Override
    public String getPath() {
        return filename;
    }
}
