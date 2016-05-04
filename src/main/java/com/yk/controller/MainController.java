package com.yk.controller;

import org.springframework.stereotype.Controller;
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
        return "login";
    }
}
