package org.example.CustomExceptions;

public class InvalidPhoneNumberException extends Exception{
    public InvalidPhoneNumberException(String message) {
        super(message);
    }
}
