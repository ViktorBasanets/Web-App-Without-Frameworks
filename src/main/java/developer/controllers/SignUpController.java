package developer.controllers;

import developer.model.User;
import developer.services.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignUpController implements Controller {

    private final UserService userService;

    public SignUpController(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void process(HttpServletRequest request, HttpServletResponse response) {

        User user = getUser(request);
        userService.createUser(user);
    }

    private User getUser(HttpServletRequest req) {

        String email = req.getParameter("email");
        String firstName = req.getParameter("firstName");
        String lastName = req.getParameter("lastName");
        String password = req.getParameter("password");
        String token = req.getParameter("token");

        return new User(email, firstName, lastName, password, token);
    }
}
