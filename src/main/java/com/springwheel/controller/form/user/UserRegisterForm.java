package com.springwheel.controller.form.user;

import org.hibernate.validator.constraints.NotEmpty;


/**
 * @author hjm
 * @Time 2016/6/4 23:20.
 */
public class UserRegisterForm {

    @NotEmpty
    private String userName;

    @NotEmpty
    private String nickName;

    private String email;

    private String passoword;

    private UserAddForm userAddForm;


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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassoword() {
        return passoword;
    }

    public void setPassoword(String passoword) {
        this.passoword = passoword;
    }

    public UserAddForm getUserAddForm() {
        return userAddForm;
    }

    public void setUserAddForm(UserAddForm userAddForm) {
        this.userAddForm = userAddForm;
    }
}
