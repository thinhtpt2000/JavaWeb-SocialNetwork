/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thinhtpt.tblusers;

import java.io.Serializable;

/**
 *
 * @author ThinhTPT
 */
public class TblUserCreateErrors implements Serializable {

    private String emailInvalid;
    private String passwordLengthErr;
    private String confirmNotMatched;
    private String fullNameLengthErr;
    private String emailIsExisted;

    public String getEmailInvalid() {
        return emailInvalid;
    }

    public void setEmailInvalid(String emailInvalid) {
        this.emailInvalid = emailInvalid;
    }

    public String getPasswordLengthErr() {
        return passwordLengthErr;
    }

    public void setPasswordLengthErr(String passwordLengthErr) {
        this.passwordLengthErr = passwordLengthErr;
    }

    public String getConfirmNotMatched() {
        return confirmNotMatched;
    }

    public void setConfirmNotMatched(String confirmNotMatched) {
        this.confirmNotMatched = confirmNotMatched;
    }

    public String getFullNameLengthErr() {
        return fullNameLengthErr;
    }

    public void setFullNameLengthErr(String fullNameLengthErr) {
        this.fullNameLengthErr = fullNameLengthErr;
    }

    public String getEmailIsExisted() {
        return emailIsExisted;
    }

    public void setEmailIsExisted(String emailIsExisted) {
        this.emailIsExisted = emailIsExisted;
    }

}
