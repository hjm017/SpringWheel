package com.yk.controller;

import com.yk.common.constants.CodeConstant;
import com.yk.common.util.Messages;
import com.yk.controller.form.site.SiteForm;
import com.yk.controller.form.site.SiteSearchForm;
import com.yk.controller.support.ControllerHelper;
import com.yk.data.domain.Site;
import com.yk.service.SiteService;
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
@RequestMapping("site")
public class SiteController {

    @Autowired
    private SiteService siteService;

    @Autowired
    private Messages messages;

    @RequestMapping("/list")
    public String index(ModelMap map, SiteSearchForm siteSearchForm) {
        //判断最小区域编码
        //省、市、区
        String region = "";
        if (StringUtils.isNotBlank(siteSearchForm.getArea())) {
            region = siteSearchForm.getArea();
        } else if (StringUtils.isNotBlank(siteSearchForm.getCity())) {
            region = siteSearchForm.getCity();
        } else if (StringUtils.isNotBlank(siteSearchForm.getProvince())) {
            region = siteSearchForm.getProvince();
        }
        siteSearchForm.setRegion(region);
        //分页查询
        Page<Site> records = siteService.findAll(siteSearchForm);
        map.addAttribute("records", records);
        //分页数据处理
        ControllerHelper.dealWithPage(siteSearchForm, records);
        map.addAttribute("page", siteSearchForm);

        return "site/list";
    }


    @RequestMapping("/toAdd")
    public String toAdd(ModelMap map){
//        String temp = messages.get(CodeConstant.ACK);
        return "site/site_add";
    }

    @RequestMapping("/add")
    public String add(ModelMap map, SiteForm siteForm){

        return ControllerHelper.showMsgPage(map, CodeConstant.SUCCESS);
    }

}
