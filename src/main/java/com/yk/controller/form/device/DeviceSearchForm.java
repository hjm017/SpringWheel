package com.yk.controller.form.device;

import com.yk.controller.form.BaseSearchForm;

/**
 * Created with IntelliJ IDEA.
 * User: hejm
 * Date: 2016/4/27
 * Time: 20:14
 * To change this template use File | Settings | File Templates.
 */
public class DeviceSearchForm extends BaseSearchForm {

    /**
     * 站点地区/名称
     */
    private String keyword;

    /**
     * 省
     */
    private String province;

    /**
     * 市
     */
    private String city;

    /**
     * 区
     */
    private String area;

    /**
     * 地区编码
     */
    private String region;

    /**
     * 设备编号
     */
    private String deviceNo;

    /**
     * 设备状态
     */
    private String status;

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
