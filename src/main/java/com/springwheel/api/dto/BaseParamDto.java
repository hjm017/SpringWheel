package com.springwheel.api.dto;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: hejm
 * Date: 2016/5/9
 * Time: 19:53
 * To change this template use File | Settings | File Templates.
 */
public class BaseParamDto implements Serializable {

    /**
     * 版本号
     */
    private String version;

    /**
     * 签名
     */
    private String sign;

    /**
     * 客户端id
     */
    private String clientId;

    /**
     * 客户端token
     */
    private String clientToken;

    /**
     * 用户token
     */
    private String userToken;

    /**
     * 时间戳
     */
    private String timestamp;

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientToken() {
        return clientToken;
    }

    public void setClientToken(String clientToken) {
        this.clientToken = clientToken;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
