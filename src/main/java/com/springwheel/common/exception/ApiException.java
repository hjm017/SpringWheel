package com.springwheel.common.exception;

import com.springwheel.api.support.ErrorCode;

/**
 * @author hjm
 * @Time 2016/6/4 10:28.
 */
public class ApiException extends RuntimeException {

    private ErrorCode errorCode;

    public ApiException(String message) {
        super(message);
    }

    public ApiException(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
