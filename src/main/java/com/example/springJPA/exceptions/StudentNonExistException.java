package com.example.springJPA.exceptions;

public class StudentNonExistException extends RuntimeException {
    public StudentNonExistException(String message) {
        super(message);
    }
}
