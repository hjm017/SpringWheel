package com.yk.common.component;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PropComponent {

    @Value("${path.css}")
    private String cssPath;

    @Value("${path.js}")
    private String jsPath;

    @Value("${path.img}")
    private String imgPath;

    @Value("${path.host}")
    private String hostPath;

    public static PropComponent getProp() {
        return SpringContext.getBean(PropComponent.class);
    }

    public String getCssPath() {
        return cssPath;
    }

    public void setCssPath(String cssPath) {
        this.cssPath = cssPath;
    }

    public String getJsPath() {
        return jsPath;
    }

    public void setJsPath(String jsPath) {
        this.jsPath = jsPath;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getHostPath() {
        return hostPath;
    }

    public void setHostPath(String hostPath) {
        this.hostPath = hostPath;
    }
}
