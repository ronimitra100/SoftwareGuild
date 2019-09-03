package com.swgcorp.flooringmastery.service;

public class CustomerNameIsRequiredException extends Exception {

    public CustomerNameIsRequiredException(String message) {
        super(message);
    }

    public CustomerNameIsRequiredException(String message, Throwable cause) {
        super(message, cause);
    }
}
