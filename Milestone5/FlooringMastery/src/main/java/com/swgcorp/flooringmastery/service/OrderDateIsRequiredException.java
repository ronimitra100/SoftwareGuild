package com.swgcorp.flooringmastery.service;

public class OrderDateIsRequiredException extends Exception {

    public OrderDateIsRequiredException(String message) {
        super(message);
    }

    public OrderDateIsRequiredException(String message, Throwable cause) {
        super(message, cause);
    }
}
