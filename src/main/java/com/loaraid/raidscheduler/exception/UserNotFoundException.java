package com.loaraid.raidscheduler.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(){
        super("User not found");
    }
    public UserNotFoundException(Long userId) {
        super(userId + " not found");
    }
}
