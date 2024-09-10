package gr.aueb.cf.school6app.authentication;

import gr.aueb.cf.school6app.dao.IUserDAO;
import gr.aueb.cf.school6app.dao.UserDAOImpl;
import gr.aueb.cf.school6app.dao.exceptions.UserDAOException;
import gr.aueb.cf.school6app.dto.UserLoginDTO;
import gr.aueb.cf.school6app.service.IUserService;
import gr.aueb.cf.school6app.service.UserServiceImpl;

public class AuthenticationProvider {
    // Wiring
    private final static IUserDAO userDAO = new UserDAOImpl();
    private final static IUserService userService = new UserServiceImpl(userDAO);

    private AuthenticationProvider() {
    }

    public static boolean authenticate(UserLoginDTO dto) throws UserDAOException {
        return userService.isUserValid(dto.getUsername(), dto.getPassword());
    }


}
