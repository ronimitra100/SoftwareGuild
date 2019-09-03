package com.sg.vendingmachine.service;

public class InvalidItemSelectionException extends Exception {

    public InvalidItemSelectionException(String message) {
        super(message);
    }

    public InvalidItemSelectionException(String message, Throwable cause) {
        super(message, cause);
    }

}
