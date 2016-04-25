package com.egenchallenge.controller;

import com.egenchallenge.exception.UserNotFoundException;
import com.egenchallenge.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



import static spark.Spark.*;

/**
 * Created by abhijit on 4/24/16.
 */
@Component
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(final UserService userService) {
        port(9900);
        this.userService = userService;

    }

    public void start() {

        get("/users", (request, response) -> userService.getAllUsers());

        post("/users", (req, res) -> userService.createUser(
                req.queryParams("user")
        ));

        put("/users/:id", (req, res) -> userService.updateUser(
                req.queryParams("user")
        ));

        exception(UserNotFoundException.class, (e, req, res) -> {
            res.status(404);
            res.body("{\"error\":\"404\" \n \"message\": \"User not found\"}");
        });
    }
}
