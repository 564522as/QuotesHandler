package com.quotes.handler.utils;

public class QuoteAlreadyExistException extends RuntimeException{
    public QuoteAlreadyExistException(String message) {
        super(message);
    }
}
