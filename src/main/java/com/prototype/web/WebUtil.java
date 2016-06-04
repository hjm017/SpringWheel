package com.prototype.web;

/**
 * Created with IntelliJ IDEA.
 * User: hejm
 * Date: 2016/4/25
 * Time: 15:20
 * To change this template use File | Settings | File Templates.
 */
public class WebUtil {

    public String getCssDomain(String uri) {
        return PropComponent.getProp().getCssDomain() + uri;
    }

    public String getJsDomain(String uri) {
        return PropComponent.getProp().getJsDomain() + uri;
    }

    public String getImgDomain(String uri) {
        return PropComponent.getProp().getImgDomain() + uri;
    }

    public String getHostDomain(String uri) {
        return PropComponent.getProp().getHostDomain() + uri;
    }

}
