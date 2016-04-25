package com.egenchallenge.service;

import com.egenchallenge.bo.User;
import com.egenchallenge.dao.UserDao;
import com.egenchallenge.exception.UserNotFoundException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


/**
 * Created by abhijit on 4/24/16.
 */

@Component
public class UserService {
    // returns a list of all users

    private final Logger logger = LoggerFactory.getLogger(UserService.class);
    private static Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @Autowired
    private UserDao userDao;

    public List<User> getAllUsers() {

        List<User> users = userDao.getAllUsers();

        return users;
    }

    // creates a new user
    public User createUser(String userJson) {
        User user = null;
        try {
            user = GSON.fromJson(userJson, User.class);
            userDao.createUser(user);
        } catch (Exception e) {
            logger.error("Exception while converting json : " + userJson);
        }
        return user;

    }
    // updates an existing user
    public User updateUser(String userJson) throws UserNotFoundException {

        User user = null;
        try {
            user = GSON.fromJson(userJson, User.class);
        } catch (Exception e) {
            logger.error("Exception while converting json : " + userJson);
            return null;
        }

        if(!doesUserExist(user.getId())) {
            logger.error("User doesn't exist");
            throw new UserNotFoundException("No user found with id: "+ user.getId());
        } else {
            if(userDao.updateUser(user) != 0){
                userDao.createUser(user);
            }
        }


        return user;

    }

    private boolean doesUserExist(String id) {
        return userDao.getUser(id) != null ? true : false;
    }

}
