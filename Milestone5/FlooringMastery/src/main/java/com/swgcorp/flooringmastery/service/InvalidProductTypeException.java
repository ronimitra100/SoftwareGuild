package com.swgcorp.flooringmastery.service;

public class InvalidProductTypeException extends Exception {

    public InvalidProductTypeException(String message) {
        super(message);
    }

    public InvalidProductTypeException(String message, Throwable cause) {
        super(message, cause);
    }
}
