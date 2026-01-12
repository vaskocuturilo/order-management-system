package com.example.ordersapi.exception;

public class OrderFoundException extends RuntimeException {

    public OrderFoundException(String message) {
        super(message);
    }
}
