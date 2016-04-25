package com.egenchallenge.dao;

import com.egenchallenge.bo.User;
import org.mongodb.morphia.Datastore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by abhijit on 4/24/16.
 */
@Repository("userDao")
public class UserDaoImpl implements UserDao {

    @Autowired
    private Datastore datastore;

    public UserDaoImpl() {}

    public UserDaoImpl(Datastore datastore) {
        this.datastore = datastore;
    }


    @Override
    public User getUser(String id) {
        return datastore.get(User.class, id);
    }

    @Override
    public List<User> getAllUsers() {
        return datastore.find(User.class).asList();
    }

    @Override
    public void createUser(User user) {
        datastore.save(user);

    }

    @Override
    public int updateUser(User user) {

        User existingUser = getUser(user.getId());
        if(existingUser != null && existingUser.getId() != null && !existingUser.getId().equals("")) {
            return -1;
        }

        datastore.save(user);

        return 0;
    }


}
