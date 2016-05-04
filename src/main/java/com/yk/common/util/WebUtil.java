package com.yk.common.util;

import com.yk.common.component.PropComponent;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 * User: hejm
 * Date: 2016/4/25
 * Time: 15:20
 * To change this template use File | Settings | File Templates.
 */
public class WebUtil {

    public String getCssPath(String uri) {
        return PropComponent.getProp().getCssPath() + uri;
    }

    public String getJsPath(String uri) {
        return PropComponent.getProp().getJsPath() + uri;
    }

    public String getImgPath(String uri) {
        return PropComponent.getProp().getImgPath() + uri;
    }

    public String getHostPath(String uri) {
        return PropComponent.getProp().getHostPath() + uri;
    }

}
