package com.egenchallenge.exception;

/**
 * Created by abhijit on 4/24/16.
 */
public class UserNotFoundException extends Exception {

    public UserNotFoundException() {
        super("User not found");
    }

    public UserNotFoundException(String ex) {
        super(ex);
    }
}
