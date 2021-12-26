package ru.itis.usanov.dao;

import ru.itis.usanov.model.User;

import java.sql.SQLException;

public interface UserDao {
    boolean saveUser(String username, String password);
    boolean userIsExist(String username, String password);
    boolean changeUsernameById(int id, String username) throws SQLException;
    boolean changeEmailById(int id, String email) throws SQLException;
    User getUserById(int id);
    User getUserByName(String username);
}
