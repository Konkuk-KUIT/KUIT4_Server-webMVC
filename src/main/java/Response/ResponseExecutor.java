package Response;

import Constants.ResponseType;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ResponseExecutor {
    public static void sendResponse(String infoString, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String delimiter = "/";
        int index = infoString.indexOf(delimiter);

        String actionType = infoString.substring(0, index);
        String path = infoString.substring(index);

        // redirect 시
        if(actionType.equals(ResponseType.REDIRECT.getType())){
            resp.sendRedirect(path);
        } else { // forward 시
            RequestDispatcher dispatcher = req.getRequestDispatcher(path);
            dispatcher.forward(req, resp);
        }
    }
}
