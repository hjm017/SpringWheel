package com.prototype.controller;

import com.prototype.common.annotation.ParamCheck;
import com.prototype.controller.form.user.UserRegisterForm;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author hjm
 * @Time 2016/6/4 23:06.
 */
@Controller
public class UserController {


    @ResponseBody
    @RequestMapping("/login.do")
    public void login() {

    }

    @ResponseBody
    @ParamCheck
    @RequestMapping("/register.do")
    public String register(UserRegisterForm userRegisterForm) {

        return "";
    }
}
