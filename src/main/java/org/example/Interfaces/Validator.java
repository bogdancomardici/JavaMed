package org.example.Interfaces;

public interface Validator {
    static boolean validate(String value) {
        if (value == null) {
            return false;
        }
        return true;
    }
}
