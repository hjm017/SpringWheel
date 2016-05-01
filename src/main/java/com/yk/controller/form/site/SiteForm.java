package com.yk.controller.form.site;

import javax.validation.constraints.NotNull;

/**
 * Created with IntelliJ IDEA.
 * User: hejm
 * Date: 2016/4/29
 * Time: 13:29
 * To change this template use File | Settings | File Templates.
 */
public class SiteForm {

    /**
     * 站点id
     */
    private String id;

    /**
     * 站点名称
     */
    @NotNull
    private String siteName;

    /**
     * 站点标识
     */
    @NotNull
    private String siteIdentify;

    /**
     * 负责人
     */
    @NotNull
    private String director;

    /**
     * 联系电话
     */
    @NotNull
    private String telephone;

    /**
     * 详细地址
     */
    @NotNull
    private String address;

    /**
     * 站点描述
     */
    @NotNull
    private String description;

    /**
     * 省
     */
    @NotNull
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



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getSiteIdentify() {
        return siteIdentify;
    }

    public void setSiteIdentify(String siteIdentify) {
        this.siteIdentify = siteIdentify;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
}
