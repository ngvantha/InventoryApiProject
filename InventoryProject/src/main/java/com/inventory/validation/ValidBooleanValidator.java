package com.inventory.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidBooleanValidator implements ConstraintValidator<ValidBoolean, String> {

//	@Override
//	public void initialize(ValidBoolean constraintAnnotation) {
//	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return isValidBoolean(value);
	}
	private boolean isValidBoolean(String input) {
	    return input.equalsIgnoreCase("true") || input.equalsIgnoreCase("false");
	}
}
