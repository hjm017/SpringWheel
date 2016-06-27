package com.springwheel.common.exception;

public class ApiException extends RuntimeException {

	private static final long serialVersionUID = -8634700792767837033L;

	public ErrorCode errorCode;

	public ApiException(String message, ErrorCode errorCode) {
		super(message);
		this.errorCode = errorCode;
	}
}
