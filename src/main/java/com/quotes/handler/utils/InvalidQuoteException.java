package com.quotes.handler.utils;

public class InvalidQuoteException extends RuntimeException{
    public InvalidQuoteException(String message) {
        super(message);
    }
}
