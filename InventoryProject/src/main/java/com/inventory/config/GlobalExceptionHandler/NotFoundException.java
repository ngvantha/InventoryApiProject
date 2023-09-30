package com.inventory.config.GlobalExceptionHandler;

public class NotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public NotFoundException() {
		super("No Information Found!!!");
	}
}
