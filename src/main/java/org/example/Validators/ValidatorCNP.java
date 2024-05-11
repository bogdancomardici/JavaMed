package org.example.Validators;

import org.example.Interfaces.Validator;

public abstract class ValidatorCNP implements Validator {
    public static boolean validate(String cnp) {
        if (cnp.length() != 13) {
            return false;
        }
        for (int i = 0; i < cnp.length(); i++) {
            if (!Character.isDigit(cnp.charAt(i))) {
                return false;
            }
        }
        return true;
    }
}
