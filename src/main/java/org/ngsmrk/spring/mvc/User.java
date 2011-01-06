/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ngsmrk.spring.mvc;

import java.io.Serializable;

/**
 *
 * @author Angus
 */
class User implements Serializable {
    private String userName;
    private String password;
    private String remark;

    public User() {
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark the remark to set
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }
    
    @Override
    public String toString() {

        StringBuilder b = new StringBuilder();
        b.append("User ").append(getUserName());
        b.append(", ").append(getPassword());
        b.append(", ").append(getRemark());

        return b.toString();
    }
}
