package com.springwheel.api.dto;

import org.hibernate.validator.constraints.NotEmpty;

import java.util.Map;

/**
 * User: hejm
 * Date: 2016/6/27
 * Time: 15:23
 */
public class IdDto {

    @NotEmpty
    private String              id;

    private Map<String, String> extra;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map<String, String> getExtra() {
        return extra;
    }

    public void setExtra(Map<String, String> extra) {
        this.extra = extra;
    }
}
