package ru.itis.usanov.dao.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.itis.usanov.dao.UserDao;
import ru.itis.usanov.helper.PasswordHelper;
import ru.itis.usanov.helper.PostgresConnectionHelper;
import ru.itis.usanov.helper.UserHelper;
import ru.itis.usanov.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao {

    public static final Logger LOGGER = LoggerFactory.getLogger(UserDaoImpl.class);

    private final Connection connection = PostgresConnectionHelper.getConnection();

    @Override
    public boolean saveUser(String username, String password) {

        User user = UserHelper.createNewUser(username, password);
        String sql = "INSERT INTO users(username, password) VALUES (?, ?)";

        if (user == null) {
            return false;
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException trowables) {
            LOGGER.warn("Failed to save user", trowables);
        }

        return false;
    }

    @Override
    public boolean userIsExist(String username, String password) {

        String sql = "SELECT * FROM users where username LIKE ? AND password LIKE ?";

        if (username == null || password == null) {
            return false;
        }

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, PasswordHelper.encrypt(password));
            if (getUserFromDb(preparedStatement) != null) {
                return true;
            }
        } catch (SQLException trowables) {
            LOGGER.warn("Failed execute userIsExist", trowables);
        }
        return false;
    }

    @Override
    public boolean changeUsernameById(int id, String username) throws SQLException {

        String sql = "UPDATE users SET username = ? WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, username);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
            return true;
        }
    }

    @Override
    public boolean changeEmailById(int id, String email) throws SQLException {

        String sql = "UPDATE users SET email = ? WHERE id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, email);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
            return true;
        }
    }

    @Override
    public User getUserById(int id) {

        String sql = "SELECT * FROM users where id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            return getUserFromDb(preparedStatement);
        } catch (SQLException trowables) {
            LOGGER.warn("Failed execute getUserById", trowables);
        }
        return null;
    }

    @Override
    public User getUserByName(String username) {

        String sql = "SELECT * FROM users where username LIKE ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, username);
            return getUserFromDb(preparedStatement);
        } catch (SQLException trowables) {
            LOGGER.warn("Failed execute getUserByName", trowables);
        }
        return null;
    }

    private User getUserFromDb(PreparedStatement preparedStatement) throws SQLException {

        ResultSet resultSet = preparedStatement.executeQuery();
        User user = null;

        if (resultSet.next()) {
            user = new User();
            user.setId(resultSet.getInt("id"));
            user.setUsername(resultSet.getString("username"));
            user.setPassword(resultSet.getString("password"));
            user.setEmail(resultSet.getString("email"));
        }

        return user;
    }
}
