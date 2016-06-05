package com.prototype.controller;

import com.prototype.controller.support.ControllerHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created with IntelliJ IDEA.
 * User: hejm
 * Date: 2016/5/3
 * Time: 16:56
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class MainController {

    @RequestMapping("/index.do")
    public String index() {
        ControllerHelper.showMsgPage(new ModelMap(), "22");
        return "login_1";
    }

    @RequestMapping("/main.do")
    public String main() {
        return "index";
    }
}
