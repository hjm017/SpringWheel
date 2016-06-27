package com.springwheel.api.support;

/**
 * @author hjm
 * @Time 2016/5/1 20:27.
 */
public class ApiResult {

    public int    code;
    public String message;

    public ApiResult() {
    }

    public ApiResult(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public ApiResult(ApiCode apiCode){
        this.code = apiCode.code;
        this.message = apiCode.message;
    }
}