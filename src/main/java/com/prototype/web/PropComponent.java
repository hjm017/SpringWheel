package com.prototype.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PropComponent {

    @Value("${domain.css}")
    private String cssDomain;

    @Value("${domain.js}")
    private String jsDomain;

    @Value("${domain.img}")
    private String imgDomain;

    @Value("${domain.host}")
    private String hostDomain;

    @Value("${application.devMode}")
    private boolean devMode;

    /**
     * 请求日志开关，默认为关闭状态
     */
    @Value("${application.request.switch}")
    private String requestSwitch = "off";


    public static PropComponent getProp() {
        return SpringContext.getBean(PropComponent.class);
    }

    public String getCssDomain() {
        return cssDomain;
    }

    public void setCssDomain(String cssDomain) {
        this.cssDomain = cssDomain;
    }

    public String getJsDomain() {
        return jsDomain;
    }

    public void setJsDomain(String jsDomain) {
        this.jsDomain = jsDomain;
    }

    public String getImgDomain() {
        return imgDomain;
    }

    public void setImgDomain(String imgDomain) {
        this.imgDomain = imgDomain;
    }

    public String getHostDomain() {
        return hostDomain;
    }

    public void setHostDomain(String hostDomain) {
        this.hostDomain = hostDomain;
    }

    public boolean isDevMode() {
        return devMode;
    }

    public void setDevMode(boolean devMode) {
        this.devMode = devMode;
    }

    public String getRequestSwitch() {
        return requestSwitch;
    }

    public void setRequestSwitch(String requestSwitch) {
        this.requestSwitch = requestSwitch;
    }
}
