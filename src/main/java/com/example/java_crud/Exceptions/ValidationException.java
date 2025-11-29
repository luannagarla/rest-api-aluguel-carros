package com.example.java_crud.Exceptions;

public class ValidationException extends RuntimeException {
    public ValidationException(String msg) {
        super(msg);
    }
}
