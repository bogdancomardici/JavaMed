package org.example.CustomExceptions;

public class InvalidDateException extends Exception{
    public InvalidDateException(String message) {
        super(message);
    }
}
