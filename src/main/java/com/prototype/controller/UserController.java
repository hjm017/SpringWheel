package com.prototype.controller;

import com.prototype.common.annotation.ParamCheck;
import com.prototype.controller.form.user.UserRegisterForm;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import javax.validation.constraints.Digits;

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
    public String register(UserRegisterForm userRegisterForm, @NotEmpty(message = "设备类型不能为空") @RequestParam(value = "facilityId", required = false) String facilityId,
                           @Digits(fraction = 6, integer = 3) @RequestParam(value = "longitude", required = false) Double longitude,
                           @Digits(fraction = 6, integer = 3) @RequestParam(value = "latitude", required = false) Double latitude,
                           @NotEmpty(message = "地址不能为空") @RequestParam(value = "address", required = false) String address,
                           @NotEmpty(message = "内容不能为空") @RequestParam(value = "content", required = false) String content) {

        return "";
    }
}
