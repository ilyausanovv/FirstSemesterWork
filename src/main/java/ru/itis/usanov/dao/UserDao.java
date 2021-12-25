package ru.itis.usanov.dao;

import ru.itis.usanov.model.User;

public interface UserDao {
    boolean saveUser(String username, String password);
    boolean userIsExist(String username, String password);
    boolean changeUsernameById(int id, String username);
    boolean changeEmailById(int id, String email);
    User getUserById(int id);
    User getUserByName(String username);
}
