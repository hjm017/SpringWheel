package com.prototype.common.validation;

import java.util.Map;

/**
 * 校验结果
 * @author wdmcygah
 *
 */
public class ValidationResult {

	//校验结果是否有错
	private boolean hasErrors;
	
	//校验错误信息
	private Map<String,String> errorMsg;

	public boolean isHasErrors() {
		return hasErrors;
	}

	public void setHasErrors(boolean hasErrors) {
		this.hasErrors = hasErrors;
	}

	public Map<String, String> getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(Map<String, String> errorMsg) {
		this.errorMsg = errorMsg;
	}

	public ValidationResult() {
	}

	public ValidationResult(boolean isHasErrors) {
		this.setHasErrors(isHasErrors);

	}
	@Override
	public String toString() {
		return "ValidationResult [hasErrors=" + hasErrors + ", errorMsg="
				+ errorMsg + "]";
	}

}
