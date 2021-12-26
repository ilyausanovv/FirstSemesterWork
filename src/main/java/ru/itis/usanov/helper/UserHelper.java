package ru.itis.usanov.helper;

import ru.itis.usanov.model.User;

public class UserHelper {

    public static User createNewUser(String username, String password){
        if(username == null || password == null){
            return null;
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(PasswordHelper.encrypt(password));
        return user;
    }
}
