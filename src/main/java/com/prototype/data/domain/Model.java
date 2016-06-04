package com.prototype.data.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
*
* @ClassName: ModelSearchForm
* @Description: 映射c_model表
* @author: carme-generator
*
*/
@Entity
@Table(name = "c_model")
public class Model implements Serializable {

    private static final long serialVersionUID = 5454155825314635342L;
    /**
    * id
    */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long              id;

    /**
    * 模板名称
    */
    @NotNull
    private String            modelName;

    /**
    * 模板类型（bs_mblx）1：竖排版  2：横排版
    */
    @NotNull
    private Integer           modelType;

    /**
    * 版式（bs_mbbs）1：图片 2：视频 3：混合版式
    */
    @NotNull
    private Integer           layout;

    /**
    * 是否使用（0：否 1：是）
    */
    @NotNull
    private Integer           isUse;

    /**
    * 是否默认（0：否 1：是）
    */
    @NotNull
    private Integer           isDefault;

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

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public Integer getModelType() {
        return modelType;
    }

    public void setModelType(Integer modelType) {
        this.modelType = modelType;
    }

    public Integer getLayout() {
        return layout;
    }

    public void setLayout(Integer layout) {
        this.layout = layout;
    }

    public Integer getIsUse() {
        return isUse;
    }

    public void setIsUse(Integer isUse) {
        this.isUse = isUse;
    }

    public Integer getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Integer isDefault) {
        this.isDefault = isDefault;
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

}