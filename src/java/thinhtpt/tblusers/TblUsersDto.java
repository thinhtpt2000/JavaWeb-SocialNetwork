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
public class TblUsersDto implements Serializable {

    private String email;
    private String password;
    private String fullName;
    private int statusId;

    public TblUsersDto(String email, String fullName, int statusId) {
        this.email = email;
        this.fullName = fullName;
        this.statusId = statusId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

}
