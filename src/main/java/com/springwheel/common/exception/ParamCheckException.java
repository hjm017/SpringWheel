package com.springwheel.common.exception;

import com.springwheel.api.support.ErrorCode;

/**
 * @author hjm
 * @Time 2016/6/4 10:28.
 */
public class ParamCheckException extends RuntimeException {

    public ErrorCode errorCode;

    public ParamCheckException(String message) {
        super(message);
    }

    public ParamCheckException( ErrorCode errorCode) {
        this.errorCode = errorCode;
    }
}
