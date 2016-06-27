package com.springwheel.common.exception;

/**
 * @author hjm
 * @Time 2016/6/4 10:28.
 */
public class ApiException extends RuntimeException {

    public ErrorCode errorCode;

    public ApiException(String message) {
        super(message);
    }

}
