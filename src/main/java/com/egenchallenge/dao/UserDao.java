package com.egenchallenge.dao;

import com.egenchallenge.bo.User;

import java.util.List;

/**
 * Created by abhijit on 4/24/16.
 */

public interface UserDao {
    User getUser(String id);
    List<User> getAllUsers();
    void createUser(User user);
    int updateUser(User user);
}
