package com.inventory.validation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidDateValidator implements ConstraintValidator<ValidDate, String> {

	private String pattern;
    @Override
    public void initialize(ValidDate constraintAnnotation) {
        this.pattern = constraintAnnotation.pattern(); // Lấy giá trị từ Annotation
    }
    
	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true; // Allow null values
        }
        return isValidDate(value);
	}
	
	private boolean isValidDate(String input) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        try {
            LocalDate.parse(input, formatter);
            return true;
        } catch (Exception e) {
            return false;
        }
	}
}
