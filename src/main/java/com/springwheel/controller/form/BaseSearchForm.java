package com.springwheel.controller.form;

import com.springwheel.common.constants.ApplicationConstant;

/**
 * Created with IntelliJ IDEA.
 * User: hejm
 * Date: 2016/4/26
 * Time: 15:13
 * To change this template use File | Settings | File Templates.
 */
public class BaseSearchForm {

    /**
     * 页大小
     */
    private Integer pageSize;
    /**
     * 当前页
     */
    private Integer currentPage;
    /**
     * 总页数
     */
    private Integer totalPage;
    /**
     * 总记录数
     */
    private Integer totalRecord;

    public BaseSearchForm() {
        this.currentPage = 1;
        this.pageSize = ApplicationConstant.PAGE_SIZE;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Integer getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(Integer totalRecord) {
        this.totalRecord = totalRecord;
    }
}
