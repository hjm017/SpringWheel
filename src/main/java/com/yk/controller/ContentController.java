package com.yk.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created with IntelliJ IDEA.
 * User: hejm
 * Date: 2016/4/25
 * Time: 13:50
 * To change this template use File | Settings | File Templates.
 */
@Controller
@RequestMapping("content")
public class ContentController {

    @RequestMapping("/list")
    public String index(ModelMap map) {

        return "content/manage_index";
    }

}
