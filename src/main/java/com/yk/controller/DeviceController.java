package com.yk.controller;

import com.yk.controller.form.device.DeviceSearchForm;
import com.yk.controller.support.ControllerHelper;
import com.yk.data.domain.Device;
import com.yk.service.DeviceService;
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
@RequestMapping("device")
public class DeviceController {

    @Autowired
    private DeviceService deviceService;

    @RequestMapping("/list")
    public String index(ModelMap map, DeviceSearchForm deviceSearchForm) {
        //判断最小区域编码
        //省、市、区
        String region = "";
        if (StringUtils.isNotBlank(deviceSearchForm.getArea())) {
            region = deviceSearchForm.getArea();
        } else if (StringUtils.isNotBlank(deviceSearchForm.getCity())) {
            region = deviceSearchForm.getCity();
        } else if (StringUtils.isNotBlank(deviceSearchForm.getProvince())) {
            region = deviceSearchForm.getProvince();
        }
        deviceSearchForm.setRegion(region);
        //分页查询
        Page<Device> records = deviceService.findAll(deviceSearchForm);
        map.addAttribute("records", records);

        //分页数据处理
        ControllerHelper.dealWithPage(deviceSearchForm, records);
        map.addAttribute("page", deviceSearchForm);

        return "device/list";
    }

}
