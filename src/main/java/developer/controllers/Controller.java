package developer.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Controller {
    void process(HttpServletRequest request, HttpServletResponse response);
}
