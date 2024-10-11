package Response;

import Response.Constants.ResponsePath;
import Response.Constants.ResponseType;

public class ResponseStringCreator {

    public static String create(ResponseType responseType, ResponsePath responsePath) {
        return responseType.getType() + responsePath.getPath();
    }
}
