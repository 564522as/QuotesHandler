package com.quotes.handler.controllers;

import com.quotes.handler.utils.*;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerController {
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public HttpEntity<ErrorBody> handleQuoteExceptions(QuoteAlreadyExistException e) {
        ErrorBody errorBody = new ErrorBody(e.getMessage(), System.currentTimeMillis());
        return new HttpEntity<>(errorBody);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public HttpEntity<ErrorBody> handleQuoteExceptions(QuoteNotExistException e) {
        ErrorBody errorBody = new ErrorBody(e.getMessage(), System.currentTimeMillis());
        return new HttpEntity<>(errorBody);
    }
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public HttpEntity<ErrorBody> handleQuoteExceptions(InvalidQuoteException e) {
        ErrorBody errorBody = new ErrorBody(e.getMessage(), System.currentTimeMillis());
        return new HttpEntity<>(errorBody);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public HttpEntity<ErrorBody> handleUserExceptions(UserNotExistException e) {
        ErrorBody errorBody = new ErrorBody(e.getMessage(), System.currentTimeMillis());
        return new HttpEntity<>(errorBody);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public HttpEntity<ErrorBody> handleUserExceptions(UserAlreadyExistException e) {
        ErrorBody errorBody = new ErrorBody(e.getMessage(), System.currentTimeMillis());
        return new HttpEntity<>(errorBody);
    }
    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public HttpEntity<ErrorBody> handleUserExceptions(InvalidUserDataException e) {
        ErrorBody errorBody = new ErrorBody(e.getMessage(), System.currentTimeMillis());
        return new HttpEntity<>(errorBody);
    }
}
