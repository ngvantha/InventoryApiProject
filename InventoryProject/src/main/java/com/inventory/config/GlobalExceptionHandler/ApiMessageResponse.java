package com.inventory.config.GlobalExceptionHandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class ApiMessageResponse {

	@NonNull
	private String message;

	@NonNull
	private String detailMessage;

	private Object error;

	@NonNull
	private Integer code;

	@NonNull
	private String moreInformation;

	public ApiMessageResponse(String message, String detailMessage) {
		this.message = message;
		this.detailMessage = detailMessage;
	}

	public ApiMessageResponse(String message, String detailMessage, Object error, Integer code) {
		this.message = message;
		this.detailMessage = detailMessage;
		this.error = error;
		this.code = code;
	}
}
