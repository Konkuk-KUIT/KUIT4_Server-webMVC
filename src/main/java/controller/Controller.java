package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Controller {
    String atGet(HttpServletRequest request, HttpServletResponse response);
    String atPost(HttpServletRequest request, HttpServletResponse response);
}
