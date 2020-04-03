package com.example.traineetask.exceptions;

public class NotFoundTaskByTitle extends RuntimeException {

    public NotFoundTaskByTitle(String message) {
        super(message);
    }
}
