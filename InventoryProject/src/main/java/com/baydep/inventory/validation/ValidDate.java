package com.baydep.inventory.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValidDateValidator.class)
public @interface ValidDate {
	String message() default "Invalid date format";

	String pattern(); // Thêm thuộc tính pattern

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
