package com.example.studentmanagement.excepetions;

public class StudentEmptyNameException extends RuntimeException{

    public StudentEmptyNameException(String message) {
        super(message);
    }
}
