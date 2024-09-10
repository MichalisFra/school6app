package gr.aueb.cf.school6app.service.exceptions;

import gr.aueb.cf.school6app.model.Teacher;
import gr.aueb.cf.school6app.model.User;

import java.io.Serial;

public class UserNotFoundException extends Exception {

    @Serial
    private static final long serialVersionUID = 1L;

    public UserNotFoundException(User user) {
        super("User with username: " + user.getUsername() + " not found");

    }

    public UserNotFoundException(String s) {
        super(s);
    }
}
