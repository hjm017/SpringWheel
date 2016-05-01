package com.yk.common.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: hejm
 * Date: 2016/4/25
 * Time: 15:20
 * To change this template use File | Settings | File Templates.
 */
@Component("webUtils")
public class WebUtil {

    @Value("${path.css}")
    private String cssPath;

    @Value("${path.js}")
    private String jsPath;

    @Value("${path.img}")
    private String imgPath;

    @Value("${path.host}")
    private String hostPath;

    public String getCssPath(String uri) {
        return cssPath + uri;
    }

    public String getJsPath(String uri) {
        return jsPath + uri;
    }

    public String getImgPath(String uri) {
        return imgPath + uri;
    }

    public String getHostPath(String uri) {
        return hostPath + uri;
    }

}
