package com.quotes.handler.utils;

public class QuoteNotExistException extends RuntimeException{
    public QuoteNotExistException(String message) {
        super(message);
    }
}
