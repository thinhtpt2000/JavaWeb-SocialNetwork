/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thinhtpt.utils;

import java.io.Serializable;
import org.apache.commons.validator.routines.EmailValidator;
import org.apache.commons.validator.routines.IntegerValidator;

/**
 *
 * @author ThinhTPT
 */
public class ValidationUitl implements Serializable {

    public static boolean isValidEmail(String email) {
        EmailValidator validator = EmailValidator.getInstance();
        return validator.isValid(email);
    }

    public static boolean isValidPageNum(String page) {
        IntegerValidator validator = IntegerValidator.getInstance();
        return validator.isValid(page);
    }
}
