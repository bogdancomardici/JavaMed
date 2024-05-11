package org.example.Validators;

import org.example.Interfaces.Validator;

public abstract class ValidatorPhoneNumber implements Validator {
    public static boolean validatePhoneNumber(String phoneNumber) {
        if (phoneNumber.length() != 10) {
            return false;
        }
        for (int i = 0; i < phoneNumber.length(); i++) {
            if (!Character.isDigit(phoneNumber.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
