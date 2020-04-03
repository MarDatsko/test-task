package com.example.traineetask.exceptions;

public class NotFoundUserByEmail extends RuntimeException {

    public NotFoundUserByEmail(String message) {
        super(message);
    }
}
