package com.springwheel.api.dto.user;

import javax.validation.constraints.NotNull;

/**
 * @author hjm
 * @Time 2016/5/1 20:29.
 */
public class AccountDto {


    @NotNull
    private String userName;

    @NotNull
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
