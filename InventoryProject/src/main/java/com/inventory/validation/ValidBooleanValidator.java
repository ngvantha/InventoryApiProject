package com.inventory.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ValidBooleanValidator implements ConstraintValidator<ValidBoolean, String> {

    @Override
    public void initialize(ValidBoolean constraintAnnotation) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value == null ||  Boolean.parseBoolean(value);
    }
}
