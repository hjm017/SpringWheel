package com.yk.controller;

import com.yk.domain.Model;
import com.yk.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created with IntelliJ IDEA.
 * User: hejm
 * Date: 2016/4/21
 * Time: 17:09
 * To change this template use File | Settings | File Templates.
 */
@Controller
public class ModelController {

    @Autowired
    private ModelService modelAdminService;

    @RequestMapping("/models")
    public String index(ModelMap map,Pageable pageable) {

        //查询一条记录
        Model model = modelAdminService.findOne(1L);

        Iterable<Model> modelList = modelAdminService.findAll(pageable);
        // 加入一个属性，用来在模板中读取
        map.addAttribute("modelList",modelList);
        map.addAttribute("host", "http://blog.didispace.com");
        map.addAttribute("imgHost", "http://127.0.0.1:8080");
        // return模板文件的名称，对应src/main/resources/templates/index.html
        return "goods";
    }
}
