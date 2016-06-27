package com.springwheel.api.support;

public enum  ApiCode {

	SUCCESS(200000,"操作成功"),
	RECORD_NOT_FOUND(300001,"未找到相关记录")
	;

	public int    code;
	public String message;

	ApiCode(int code,String message){
		this.code=code;
		this.message=message;
	}

}
