package com.prototype.api.support;

/**
 * @author hjm
 * @Time 2016/5/1 20:27.
 */
public class ErrorResult {

    public int code;
    public String message;

    public ErrorResult() {
    }

    public ErrorResult(int code, String message) {
        this.code = code;
        this.message = message;
    }
}