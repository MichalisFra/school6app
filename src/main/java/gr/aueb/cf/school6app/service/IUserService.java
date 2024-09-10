package gr.aueb.cf.school6app.service;

import gr.aueb.cf.school6app.dao.exceptions.UserDAOException;
import gr.aueb.cf.school6app.dto.InsertUserDTO;
import gr.aueb.cf.school6app.model.User;
import gr.aueb.cf.school6app.service.exceptions.UserNotFoundException;

public interface IUserService {
    User insertUser(InsertUserDTO dto) throws UserDAOException;
    User getUserByUsername(String username) throws UserNotFoundException, UserDAOException;
    boolean isUserValid(String username, String password) throws UserDAOException;
    boolean isEmailExists(String username) throws UserDAOException;

}
