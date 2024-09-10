package gr.aueb.cf.school6app.dao;

import gr.aueb.cf.school6app.dao.exceptions.UserDAOException;
import gr.aueb.cf.school6app.model.User;

public interface IUserDAO {
    User insert(User user) throws UserDAOException;
//    User update(User user) throws UserDAOException;
//    void delete(int id) throws UserDAOException;
//    List<User> getAll() throws UserDAOException;
    User getByUsername(String username) throws UserDAOException;
    boolean isUserValid(String username, String password) throws UserDAOException;
    boolean isEmailExists(String username) throws UserDAOException;
}
