package com.quotes.handler.utils;

public class InvalidUserDataException extends RuntimeException{
    public InvalidUserDataException(String message) {
        super(message);
    }
}
