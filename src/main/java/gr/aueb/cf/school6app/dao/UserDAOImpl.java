package gr.aueb.cf.school6app.dao;

import gr.aueb.cf.school6app.core.RoleType;
import gr.aueb.cf.school6app.dao.exceptions.UserDAOException;
import gr.aueb.cf.school6app.model.User;
import gr.aueb.cf.school6app.security.SecUtil;
import gr.aueb.cf.school6app.service.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOImpl implements IUserDAO{

    @Override
    public User insert(User user) throws UserDAOException {
        String sql = "INSERT INTO users (username, password, role) VALUES (?, ?, ?)";

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {

            // Extract model info
            String username = user.getUsername();
            String password = user.getPassword();
            RoleType role = user.getRoleType();

            ps.setString(1, username);
            ps.setString(2, SecUtil.hashPassword(password));
            ps.setString(3, role.name());

            ps.executeUpdate();
            // logging

            return user;


        } catch (SQLException e) {
            e.printStackTrace();
            // logging
            throw new UserDAOException("Insert SQL error. User: "  + user + " not inserted");
        }
    }

    @Override
    public User getByUsername(String username) throws UserDAOException {
        String sql = "SELECT * FROM users WHERE username = ?";
        User user = null;
        ResultSet rs;

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {


            ps.setString(1, username);

            rs = ps.executeQuery();

            if (rs.next()) {
                user = new User(rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        RoleType.valueOf(rs.getString("role")));

            }
            // logging

            return user;


        } catch (SQLException e) {
            e.printStackTrace();
            // logging
            throw new UserDAOException("SQL error with username: "  + username);
        }
    }

    @Override
    public boolean isUserValid(String username, String password) throws UserDAOException {
        String sql = "SELECT * FROM users WHERE username = ?";
        User user = null;
        ResultSet rs;

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {


            ps.setString(1, username);

            rs = ps.executeQuery();

            if (rs.next()) {
                user = new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"), RoleType.valueOf(rs.getString("role")));

            } else {
                return false;
            }
            // logging

            return SecUtil.isPasswordValid(password, user.getPassword());


        } catch (SQLException e) {
            e.printStackTrace();
            // logging
            throw new UserDAOException("SQL error in isUserValid for username: "  + username);
        }
    }

    @Override
    public boolean isEmailExists(String username) throws UserDAOException {
        String sql = "SELECT count(*) FROM users WHERE username = ?";
        User user = null;
        ResultSet rs;
        int count = 0;

        try (Connection connection = DBUtil.getConnection();
             PreparedStatement ps = connection.prepareStatement(sql)) {


            ps.setString(1, username);

            rs = ps.executeQuery();

            if (rs.next()) {
                count = rs.getInt(1);
            }
            // logging

            return count > 0;


        } catch (SQLException e) {
            e.printStackTrace();
            // logging
            throw new UserDAOException("SQL error in isEmailExists for username: "  + username);
        }
    }
}
