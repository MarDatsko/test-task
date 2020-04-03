package com.example.traineetask.exceptions;

public class NotFoundTaskById extends RuntimeException {

    public NotFoundTaskById(String message) {
        super(message);
    }
}
