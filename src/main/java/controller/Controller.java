package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public interface Controller {

    String process(HttpServletRequest req, HttpServletResponse resp) throws IOException;
}
