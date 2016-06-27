package com.springwheel.api.dto.user;

import javax.validation.constraints.NotNull;

/**
 * @author hjm
 * @Time 2016/5/1 20:29.
 */
public class UserDto {

    private String id;

    @NotNull
    private String userName;


    private String nickName;

    private String phoneNo;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
}
