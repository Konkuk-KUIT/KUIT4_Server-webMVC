package core.controller;

import core.db.Repository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignUpController implements Controller {
    private Repository userRepository;
    public SignUpController(Repository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        return "";
    }
}
