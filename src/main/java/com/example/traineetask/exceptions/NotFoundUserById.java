package com.example.traineetask.exceptions;

public class NotFoundUserById extends RuntimeException {

    public NotFoundUserById(String message) {
        super(message);
    }
}
