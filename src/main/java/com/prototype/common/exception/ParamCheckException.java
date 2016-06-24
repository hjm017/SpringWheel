package com.prototype.common.exception;

/**
 * @author hjm
 * @Time 2016/6/4 10:28.
 */
public class ParamCheckException extends RuntimeException {

    public ErrorCode errorCode;

    public ParamCheckException(String message) {
        super(message);
    }

    public ParamCheckException(String message, ErrorCode errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
}
