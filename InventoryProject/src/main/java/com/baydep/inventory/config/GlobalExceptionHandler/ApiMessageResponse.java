package com.baydep.inventory.config.GlobalExceptionHandler;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL) // Bỏ qua các trường có giá trị null trong JSON serialization
public class ApiMessageResponse extends ApiMessageBase {
	public ApiMessageResponse(String message, String detailMessage) {
		super(message, detailMessage);
	}

	private Object error;

	private Integer code;

	private String moreInformation;

	public Object getError() {
		return error;
	}

	public void setError(Object error) {
		this.error = error;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMoreInformation() {
		return moreInformation;
	}

	public void setMoreInformation(String moreInformation) {
		this.moreInformation = moreInformation;
	}

	public ApiMessageResponse(String message, String detailMessage, Object error, Integer code,
			String moreInformation) {
		super(message, detailMessage);
		this.error = error;
		this.code = code;
		this.moreInformation = moreInformation;
	}

}


