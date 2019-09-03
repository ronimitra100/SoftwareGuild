package com.swgcorp.flooringmastery.service;

public class OrderNumberIsRequiredException extends Exception {

    public OrderNumberIsRequiredException(String message) {
        super(message);
    }

    public OrderNumberIsRequiredException(String message, Throwable cause) {
        super(message, cause);
    }
}
