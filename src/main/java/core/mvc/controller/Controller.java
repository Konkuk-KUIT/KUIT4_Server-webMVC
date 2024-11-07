package core.mvc.controller;

import jwp.model.User;

import javax.servlet.http.HttpSession;

public interface Controller {

    default void setSession(HttpSession session) {
    }

    default void setUserFromSession(User user) {
    }
}
