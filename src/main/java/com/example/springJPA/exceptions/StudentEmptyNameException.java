package com.example.springJPA.exceptions;

public class StudentEmptyNameException extends RuntimeException {
    public StudentEmptyNameException(String message) {
        super(message);
    }
}
