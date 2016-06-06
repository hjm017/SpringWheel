package com.prototype.controller.form.user;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author hjm
 * @Time 2016/6/4 23:20.
 */
public class UserAddForm {

    @NotEmpty
    private String qq;


    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }
}
