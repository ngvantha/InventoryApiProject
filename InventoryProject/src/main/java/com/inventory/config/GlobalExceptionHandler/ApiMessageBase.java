package com.inventory.config.GlobalExceptionHandler;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ApiMessageBase {
	private String message;
	private String detailMessage;
}
