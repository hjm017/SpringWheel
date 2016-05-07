package com.yk.data.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
*
* @ClassName: Site
* @Description: 映射w_site表
* @author: carme-generator
*
*/
@Entity
@Table(name = "w_site")
public class Site implements Serializable {

    private static final long serialVersionUID = 5454155825314635342L;
    /**
    * id
    */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long              id;

    /**
    * 站点名称
    */
    @NotNull
    private String            siteName;

    /**
    * 站点标识
    */
    @NotNull
    private String            siteIdentify;

    /**
    * 负责人
    */
    @NotNull
    private String            director;

    /**
    * 站点电话
    */
    @NotNull
    private String            telephone;

    /**
    * 地区
    */
    @NotNull
    private String            region;

    /**
    * 详细地址
    */
    @NotNull
    private String            address;

    /**
    * 经度
    */
    @NotNull
    private BigDecimal        longitude;

    /**
    * 纬度
    */
    @NotNull
    private BigDecimal        latitude;

    /**
    * 站点描述
    */
    @NotNull
    private String            description;

    /**
     * 站点设备数量
     */
    @NotNull
    private Integer           deviceAmount;

    /**
     * 站点异常设备数量
     */
    @NotNull
    private Integer           deviceError;

    /**
    * 是否删除（0：未删除 1：已删除）
    */
    @NotNull
    private Integer           isDelete;

    /**
    * 创建时间
    */
    @NotNull
    private Date              createdAt;

    /**
    * 创建人
    */
    @NotNull
    private String            createdBy;

    /**
    * 更新时间
    */
    private Date              changedAt;

    /**
    * 更新人
    */
    private String            changedBy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getChangedAt() {
        return changedAt;
    }

    public void setChangedAt(Date changedAt) {
        this.changedAt = changedAt;
    }

    public String getChangedBy() {
        return changedBy;
    }

    public void setChangedBy(String changedBy) {
        this.changedBy = changedBy;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public Integer getDeviceAmount() {
        return deviceAmount;
    }

    public void setDeviceAmount(Integer deviceAmount) {
        this.deviceAmount = deviceAmount;
    }

    public Integer getDeviceError() {
        return deviceError;
    }

    public void setDeviceError(Integer deviceError) {
        this.deviceError = deviceError;
    }
}