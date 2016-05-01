package com.yk.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
*
* @ClassName: Device
* @Description: 映射f_device表
* @author: carme-generator
*
*/
@Entity
@Table(name = "f_device")
public class Device implements Serializable {

    private static final long serialVersionUID = 5454155825314635342L;
    /**
    * id
    */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long              id;

    /**
     * 设备编号
     */
    private Integer           deviceNo;

    /**
    * 备注
    */
    @NotNull
    private String            remark;

    /**
    * 站点id
    */
    @NotNull
    @Column(name = "w_site_id")
    private Long              siteId;

    /**
    * 站点名称
    */
    @NotNull
    private String            siteName;

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
    * 负责人
    */
    @NotNull
    private String            director;

    /**
    * 联系电话
    */
    @NotNull
    private String            telephone;

    /**
    * 状态（bs_sbzt）1：离线 2：在线
    */
    @NotNull
    private Integer           status;

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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getSiteId() {
        return siteId;
    }

    public void setSiteId(Long siteId) {
        this.siteId = siteId;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public Integer getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(Integer deviceNo) {
        this.deviceNo = deviceNo;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
}